package com.github.alonwang.design_pattern.decorator;

/**
 * Created by dvWang on 2017/5/4.
 */
public abstract class Decorator extends Sheep {
    protected Sheep sheep;
    public Decorator(){}
    public Decorator(Sheep sheep){this.sheep=sheep;}
}
