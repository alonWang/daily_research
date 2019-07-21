package com.github.alonwang.object;

/**
 * @description: Java中的线程虚假唤醒演示 参考了 https://www.jianshu.com/p/da312eee4ac4
 * @author: alonwang
 * @create: 2019-07-19 15:54
 **/
public class SpuriousWakeUp {
    private final Object lock = new Object();
    private int product = 0;
    //如果没有产品,在lock对象上等待唤醒,如果有产品,消费.
    private Runnable consumer = () -> {
        System.out.println(Thread.currentThread().getName() + " prepare consume");
        synchronized (lock) {
            if (product <= 0) {//替换为while解决线程虚假唤醒问题
                try {
                    System.out.println(Thread.currentThread().getName() + " wait");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " wakeup");
            }
            product--;
            System.out.println(Thread.currentThread().getName() + " consumed product:" + product);
            if (product < 0) {
                System.err.println(Thread.currentThread().getName() + " spurious lock happend, product: " + product);
            }
        }
    };
    //生产一个产品然后唤醒一个在lock对象上等待的consumer
    private Runnable producer = () -> {
        System.out.println(Thread.currentThread().getName() + " prepare produce");
        synchronized (lock) {
            product += 1;
            System.out.println(Thread.currentThread().getName() + "produced product: " + product);
            lock.notify();
        }
    };

    public void producerAndConsumer() {
        // 启动2个consumer,1个producer
        Thread c1 = new Thread(consumer);
        Thread c2 = new Thread(consumer);
        Thread p = new Thread(producer);
        c1.start();
        c2.start();
        p.start();

    }

    public static void main(String[] args) {
        //运行100次,以便触发异常现象
        for (int i = 0; i < 100; i++) {
            new SpuriousWakeUp().producerAndConsumer();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}