package com.github.alonwang.lang;

/**
 * @author alonwang
 * @date 2020/12/17 22:18
 */
public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            Thread th = Thread.currentThread();
            while (true) {
                if (th.isInterrupted()) {
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    th.interrupt();
                }
            }
        });
        t.start();
        Thread.sleep(10);
        t.interrupt();
    }
}
