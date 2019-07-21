package com.github.alonwang.object;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * wait/notify的演示
 */
public class SynchronizedTest {
    private static final Object lock = new Object();
    private static final int THREAD_COUNT = 5;

    public static void main(String[] args) {
        synchronizedTest();
        notifyTest();
        notifyAllTest();
    }

    private static void synchronizedTest() {
        System.out.println("start synchronized test...");
        //等待所有线程执行结束
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
        IntStream.rangeClosed(1, THREAD_COUNT).forEach(i -> {
            //模拟每个线程执行三次业务操作
            new Thread(() -> {
                for (int k = 0; k < 3; k++) {
                    synchronized (lock) {
                        System.out.println(Thread.currentThread().getName() + " execute " + k);
                        try {
                            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(100));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //睡眠一会,让其他线程有机会获取同步锁.
                    try {
                        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(Thread.currentThread().getName() + " execute finished");
                latch.countDown();
            }, "synchronized-Thread-" + i).start();
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("synchronized test finish...");
    }

    /**
     * notify调用后,当前线程会在同步代码块执行完成后,再释放锁.并不是notify调用后直接释放锁.
     * wait调用后当前线程会保存线程上下文,释放锁,进入等待状态.被notify唤醒后,恢复线程上下文,继续执行.
     * 下例中wait-Thread运行后在lockObject上等待,而后被notify-Thread唤醒.
     * 在notifyThread执行notify后,并没有立刻释放锁,而是在同步块执行结束后才释放的锁.
     * console:
     * wait-before
     * notify-before
     * notify-after
     * wait-after
     */
    private static void notifyTest() {
        System.out.println("notify test start...");
        CountDownLatch finishLatch = new CountDownLatch(2);
        //确保wait-Thread在notify-Thread之前进入同步代码块.
        CountDownLatch waitLatch = new CountDownLatch(1);
        new Thread(() -> {
            synchronized (lock) {
                waitLatch.countDown();
                System.out.println("wait-before");

                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("wait-after");
            }
            finishLatch.countDown();
        }, "wait-Thread").start();
        new Thread(() -> {
            try {
                waitLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                System.out.println("notify-before");
                lock.notify();
                System.out.println("notify-after");
            }
            finishLatch.countDown();
        }, "notify-Thread").start();
        try {
            finishLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("notify test finish...");
    }

    /**
     * console:
     * wait-before-wait-Thread-1
     * wait-before-wait-Thread-3
     * wait-before-wait-Thread-2
     * wait-before-wait-Thread-4
     * wait-before-wait-Thread-5
     * notify-before
     * notify-after
     * wait-after-wait-Thread-5
     * wait-after-wait-Thread-4
     * wait-after-wait-Thread-2
     * wait-after-wait-Thread-3
     * wait-after-wait-Thread-1
     */
    private static void notifyAllTest() {
        System.out.println("notifyAll test start...");
        CountDownLatch finishLatch = new CountDownLatch(THREAD_COUNT + 1);
        //确保wait-Thread在notify-Thread之前进入同步代码块.
        CountDownLatch waitLatch = new CountDownLatch(THREAD_COUNT);
        IntStream.rangeClosed(1, THREAD_COUNT).forEach(i -> {
            new Thread(() -> {
                synchronized (lock) {
                    waitLatch.countDown();
                    System.out.println("wait-before-" + Thread.currentThread().getName());
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("wait-after-" + Thread.currentThread().getName());
                }
                finishLatch.countDown();
            }, "wait-Thread-" + i).start();
        });

        new Thread(() -> {
            try {
                waitLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                System.out.println("notify-before");
                lock.notifyAll();
                System.out.println("notify-after");
            }
            finishLatch.countDown();
        }, "notify-Thread").start();

        try {
            finishLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("notifyAll test finish...");
    }
}
