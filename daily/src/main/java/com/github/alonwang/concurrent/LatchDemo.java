package com.github.alonwang.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * CountdownLatch演示
 * count初始为3,
 * 线程t1,t2,t3各自完成一项耗时任务(以sleep模拟),并在执行结束后countdown
 * 主线程await等待
 *
 * @author alonwang
 * @date 2020/11/30 7:55 上午
 */
public class LatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        Runnable r = () -> {
            //执行一项耗时任务
            try {
                Thread.sleep(new Random().nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行完毕...");
            latch.countDown();

        };
        new Thread(r, "t1").start();
        new Thread(r, "t2").start();
        new Thread(r, "t3").start();
        latch.await();
        System.out.println("主线程执行完毕 ...");
    }
}
