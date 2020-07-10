package com.github.alonwang.lang.instrumentation;

/**
 * @author alonwang
 * @date 2020/5/18 20:12
 * @detail
 */
public class Application {
    public static void main(String[] args) throws InterruptedException {
        RedefineClassesLimit limit = new RedefineClassesLimit();
        while (true) {
            limit.test();
            Thread.sleep(5000);
        }
    }
}
