package com.github.alonwang.lang;

/**
 * @author alonwang
 * @date 2020/12/17 22:18
 */
public class InterruptDemo {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            Thread th = Thread.currentThread();
            while (true) {
                if (th.isInterrupted()) {
                    //调用Thread.interrupted()静态方法清除中断状态
                    Thread.interrupted();
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        t.interrupt();


    }
}
