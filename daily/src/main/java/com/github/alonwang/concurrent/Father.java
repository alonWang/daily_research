package com.github.alonwang.concurrent;

/**
 * @author alonwang
 * @date 2020/11/28 11:49 上午
 */
public class Father {
    public synchronized void doSomething()  {
        //留时间打印线程堆栈
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
