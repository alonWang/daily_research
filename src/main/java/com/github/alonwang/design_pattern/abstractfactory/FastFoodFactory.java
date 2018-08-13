package com.github.alonwang.design_pattern.abstractfactory;

/**
 * Created by dvWang on 2017/5/18.
 */
public abstract class FastFoodFactory {
    public abstract Hamburg createHamburg(float price,String name);
    public abstract Cola createCola(float price,String name);
}
