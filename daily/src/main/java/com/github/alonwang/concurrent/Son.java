package com.github.alonwang.concurrent;

/**
 * @author alonwang
 * @date 2020/11/28 11:51 上午
 */
public class Son extends Father {
    @Override
    public synchronized void doSomething()  {
        doAnotherThing();
    }
    public synchronized  void doAnotherThing()  {
        super.doSomething();
    }
}
