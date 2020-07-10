package com.github.alonwang.lang;

/**
 * 一个有趣的问题,启动后永远不会结束运行,原因在于 main线程在等待init-thread运行结束,
 * 而init-thread在等待JoinAndStatic的类初始化锁,这个这个锁又被main线程掌握,因此死锁.
 * 使用匿名内部类的形式,如果不去修改initialized值,只是输出一些数据,不会造成死锁.
 * 如果使用lambda形式,无论是否修改都会死锁.
 *
 * @author alonwang
 * @date 2020/5/26 11:02 下午
 * @detail
 */
public class JoinAndStaticInit {
    private static boolean initialized = false;

    static {
        Thread t = new Thread(/*()-> System.out.println("thread run")*/
                new Runnable() {
                    @Override
                    public void run() {
                        initialized = true;
                        System.out.println("thread run");
                    }
                }, "init-thread");
        t.start();
        try {
            t.join();
        } catch (Exception e) {
            throw new AssertionError();
        }
    }

    public static void main(String[] args) {
        System.out.println(initialized);
    }
}
