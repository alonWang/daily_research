package com.designpattern.abstractfactory;

/**
 * Created by dvWang on 2017/5/18.
 */
public class KFCFactory extends FastFoodFactory {
    @Override
    public Hamburg createHamburg(float price, String name) {
        return new KFCHamburg(price,name);
    }

    @Override
    public Cola createCola(float price, String name) {
        return new KFCCola(price,name);
    }
}
