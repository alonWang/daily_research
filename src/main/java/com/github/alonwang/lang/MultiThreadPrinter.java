package com.github.alonwang.lang;

/**
 * 两个线程交替打印数字
 */
public class MultiThreadPrinter {
    static class ThreadPrinter implements Runnable {
        private int num;
        private final int endInclude;
        private final Object lock;

        public ThreadPrinter(int num, int endInclude, Object lock) {
            this.num = num;
            this.endInclude = endInclude;
            this.lock = lock;
        }

        @Override
        public void run() {
            while (num <= endInclude) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + " : " + num);
                    num += 2;
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
    }

    public static void main(String[] args) {
        Object lock = new Object();
        new Thread(new ThreadPrinter(0, 100, lock), "even").start();
        new Thread(new ThreadPrinter(1, 100, lock), "odd").start();
    }

}
