package com.github.alonwang.lang;

/**
 * 两个线程交替打印数字
 */
public class MultiThreadPrinter {
    private int num;
    private final int endInclude;
    private final Object lock = new Object();

    public MultiThreadPrinter(int num, int endInclude) {
        this.num = num;
        this.endInclude = endInclude;
    }

    public void print() {
        while (num <= endInclude) {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " : " + num++);
                lock.notify();
                if (num <= endInclude) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        MultiThreadPrinter printer = new MultiThreadPrinter(0, 100);
        new Thread(printer::print, "even").start();
        new Thread(printer::print, "odd").start();
    }

}
