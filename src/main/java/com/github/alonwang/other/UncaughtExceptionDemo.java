package com.github.alonwang.other;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class UncaughtExceptionDemo {
    static {
        Thread.setDefaultUncaughtExceptionHandler((Thread t, Throwable e) -> {
            System.out.println(String.format("default exception handler. thread: %s throw exception %s", t.getName(), e));
        });
    }

    public static void main(String[] args) throws InterruptedException {
        singleThread();
        threadPool();
    }

    private static void singleThread() throws InterruptedException {
        Thread newThread = new Thread(() -> {
            throw new NullPointerException("single thread");
        });
        newThread.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
            System.out.println(String.format("single thread handler. thread: %s throw exception %s", t.getName(), e));
        });
        newThread.start();
        //等待线程执行完成
        Thread.sleep(100);
    }

    private static void threadPool() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(Thread.currentThread().getThreadGroup(), r);
                thread.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
                    System.out.println(String.format("thread pool handler. thread: %s throw exception %s", t.getName(), e));
                });
                return thread;
            }
        });
        executorService.execute(() -> {
            throw new NullPointerException("thread pool");
        });
        //等待任务执行完成
        Thread.sleep(2000);
        executorService.shutdown();
    }
}
