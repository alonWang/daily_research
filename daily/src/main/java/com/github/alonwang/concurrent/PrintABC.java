package com.github.alonwang.concurrent;

import lombok.SneakyThrows;
import org.apache.poi.ss.formula.functions.T;
import org.checkerframework.checker.units.qual.C;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 多线程循环打印ABC
 *
 * @author alonwang
 * @date 2021/3/10 12:36 下午
 */
public class PrintABC {
    /**
     * 三个线程组成环,当前线程完成后,通过信号量提醒下一个线程,每个线程各自维护打印次数
     */
    static class SemaphoreWay implements Runnable {
        class Printer implements Runnable {
            private Semaphore self;
            private Semaphore next;
            private char c;
            private int count;

            public Printer(Semaphore self, Semaphore next, char c, int count) {
                this.self = self;
                this.next = next;
                this.c = c;
                this.count = count;
            }

            @Override
            public void run() {
                try {
                    while (count > 0) {
                        self.acquire();
                        System.out.println(Thread.currentThread().getName() + " " + count + " " + c);
                        count--;
                        next.release();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @SneakyThrows
        @Override
        public void run() {
            Semaphore a = new Semaphore(1);
            Semaphore b = new Semaphore(0);
            Semaphore c = new Semaphore(0);

            new Thread(new Printer(a, b, 'A', 10), "T-1").start();
            new Thread(new Printer(b, c, 'B', 10), "T-2").start();
            Thread t3 = new Thread(new Printer(c, a, 'C', 10), "T-3");
            t3.start();
            t3.join();
        }


    }

    /**
     * synchronized/notify方式
     */
    static class SynchronizedWay implements Runnable {
        private int count;
        private Object monitor;
        private char nextChar;

        class Printer implements Runnable {
            private char c;
            /**
             * 是否为最后一个线程,控制次数
             */
            private boolean last;

            public Printer(char c, boolean last) {
                this.c = c;
                this.last = last;

            }

            @SneakyThrows
            @Override
            public void run() {
                synchronized (monitor) {
                    while (count > 0) {
                        //强制while,被唤醒后要先检查自己的字符是否符合
                        while (c != nextChar) {
                            monitor.wait();
                        }
                        //count是共享变量,唤醒前后的值可能不同,需要检查
                        if (count <= 0) {
                            return;
                        }
                        System.out.println(Thread.currentThread().getName() + " " + count + " " + c);
                        if (last) {
                            count--;
                        }
                        nextChar = (char) ('A' + (nextChar - 'A' + 1) % 3);
                        monitor.notifyAll();
                        monitor.wait();
                    }
                }
            }
        }

        @SneakyThrows
        @Override
        public void run() {
            count = 10;
            monitor = new Object();
            nextChar = 'A';
            new Thread(new Printer('A', false),"T-1").start();
            new Thread(new Printer('B', false), "T-2").start();
            Thread t3=new Thread(new Printer('C', true), "T-3");
            t3.start();
            t3.join();

        }
    }

    /**
     * Lock/condition方式
     */
    static class LockWay implements Runnable{
        private Lock lock;
        private int count;

        public LockWay(Lock lock,int count) {
            this.lock = lock;
            this.count=count;
        }

        class Printer implements Runnable{
            private Condition self;
            private Condition next;
            private char c;
            private boolean last;

            public Printer(Condition self, Condition next, char c,boolean last) {
                this.self = self;
                this.next = next;
                this.c = c;
                this.last=last;
            }

            @Override
            public void run() {
                lock.lock();
            }
        }
        @Override
        public void run() {

        }
    }
    public static void main(String[] args) {
        //new SemaphoreWay().run();
        //new SynchronizedWay().run();
    }
}
