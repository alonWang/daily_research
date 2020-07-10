package com.github.alonwang.design_pattern.abstractfactory;

/**
 * Created by dvWang on 2017/5/18.
 */
public class McDonaldsFactory extends FastFoodFactory {
    @Override
    public Hamburg createHamburg(float price, String name) {
        return new McDonaldsHamburg(price,name);
    }

    @Override
    public Cola createCola(float price, String name) {
        return new McDonaldsCola(price,name);
    }
}
