package com.github.alonwang.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * CountdownLatch演示
 * count初始为3,
 * 线程t1,t2各自完成一项耗时任务(以sleep模拟),完成后countdown
 * 其中t1countdown两次
 * 主线程wait等待
 *
 * @author alonwang
 * @date 2020/11/30 7:55 上午
 */
public class LatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        Thread t1 = new Thread(() -> {
            latch.countDown();
            System.out.println("t1 countdown...");
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            System.out.println("t1 countdown again");
        }, "t1");
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            System.out.println("t2 countdown...");
        }, "t2");
        t1.start();
        t2.start();
        latch.await();
        System.out.println(" finish...");
    }
}
