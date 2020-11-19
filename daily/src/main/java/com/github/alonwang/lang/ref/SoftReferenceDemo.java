package com.github.alonwang.lang.ref;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 软引用回收和LRU策略测试
 * JDK版本 Oracle JDK13
 * JVM启动参数
 * -Xmx10m
 * -Xms10m
 * -XX:-DisableExplicitGC
 * -XX:SoftRefLRUPolicyMSPerMB=1000
 * -verbose:gc
 *
 * 持续向内存中添加4KB大小的软引用对象,直到确保堆无法容纳所有对象.并间隙性强制触发GC.
 * 在添加结束后, 打印其中三个对象的信息:
 * A最早被创建,在运行过程中不断被访问
 * B在添加一部分后创建,之后没有访问过
 * C在偏后期创建,之后没有访问过
 *
 * 最终结果是
 * A未被回收
 * B被回收
 * C是否被回收 取决于 SoftRefLRUPolicyMSPerMB的值
 * * 为1时被回收
 * * 为1000时未被回收
 *
 * @author alonwang
 * @date 2020/11/19 10:32
 */
public class SoftReferenceDemo {
    /**
     * 近似4KB的内存块
     * 4024 byte
     * @return
     */
    private static int[] newBlock() {
        return new int[1000];
    }

    private static List<SoftReference<int[]>> list = new ArrayList<>();
    private static Object A(){
        return list.get(0).get();
    }
    private static Object B(){
        return list.get(999).get();
    }
    private static Object C(){
        return list.get(2099).get();
    }

    public static void main(String[] args) throws InterruptedException {
            for (int i = 0; i < 2500; i++) {
                list.add(new SoftReference<>(newBlock()));
                if (i%100==0){
                    System.gc();
                    A();
                    Thread.sleep(100);
                }
            }



        System.out.println("A: "+ A());
        System.out.println("B: "+ B());
        System.out.println("C: "+ C());




    }
}
