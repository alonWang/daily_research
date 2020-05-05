package com.github.alonwang.concurrent;


import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.Semaphore;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-09-09 08:33
 **/
@ThreadSafe
public class MultiThreadPrinter {
    private int num;
    private final int endInclude;
    private final int threadCount;

    public MultiThreadPrinter(int num, int endInclude, int threadCount) {
        if (endInclude < num) {
            throw new IllegalArgumentException("endInclude can't less than num");
        }
        if (threadCount <= 0 || threadCount > (endInclude - num + 1)) {
            throw new IllegalArgumentException("threadCount illegal");
        }
        this.num = num;
        this.endInclude = endInclude;
        this.threadCount = threadCount;

    }

    public void print() {
        Semaphore[] locks = new Semaphore[threadCount];
        for (int i = 0; i < threadCount; i++) {
            locks[i] = new Semaphore(0);
        }
        for (int i = 0; i < threadCount; i++) {
            startWorkerThread(i, locks);
        }
        locks[0].release();
    }

    private void startWorkerThread(int i, Semaphore[] locks) {
        Runnable r = () -> {
            while (num <= endInclude) {
                try {
                    locks[getId(i)].acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (num > endInclude) {
                    locks[getId(i + 1)].release();
                    return;
                }

                System.out.println(Thread.currentThread().getName() + ": " + (num++));
                locks[getId(i + 1)].release();
            }
        };
        new Thread(r, "t-" + i).start();
    }

    private int getId(int i) {
        return i % threadCount;
    }

    public static void main(String[] args) {
        new MultiThreadPrinter(1006, 1169, 101).print();
    }
}
