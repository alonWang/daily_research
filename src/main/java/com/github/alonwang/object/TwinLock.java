package com.github.alonwang.object;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TwinLock implements Lock {
    private final Sync sync = new Sync(2);

    private static class Sync extends AbstractQueuedSynchronizer {
        public Sync(int count) {
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            for (; ; ) {
                int current = getState();
                int newState = current - arg;
                if (newState < 0 || compareAndSetState(current, newState)) {
                    return newState;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (; ; ) {
                int current = getState();
                int newState = current + arg;
                if (compareAndSetState(current, newState)) {
                    return true;
                }
            }
        }
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    public static void main(String[] args) {
        TwinLock lock = new TwinLock();
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + " start");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " get lock");

            System.out.println(Thread.currentThread().getName() + " running");
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " unlock");

            }
        };
        for (int i = 1; i <= 50; i++) {
            new Thread(r, "t-" + i).start();
        }
    }
}
