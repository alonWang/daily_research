package com.github.alonwang.concurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author alonwang
 * @date 2020/11/30 8:04 上午
 */
public class CycleBarrierDemo {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier=new CyclicBarrier(2, ()-> System.out.println(System.currentTimeMillis()+" finish..."));
        Runnable runnable = ()->{
            try {
                Thread.sleep(new Random().nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis()+Thread.currentThread().getName()+" finish");

            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        };
        Thread t1=new Thread(runnable,"t1" );
        Thread t2 = new Thread(runnable,"t2");
        t1.start();
        t2.start();

        Thread.sleep(5000);

    }
}
