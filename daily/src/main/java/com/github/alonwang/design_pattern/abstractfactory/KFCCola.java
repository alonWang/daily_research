package com.github.alonwang.design_pattern.abstractfactory;

/**
 * Created by dvWang on 2017/5/18.
 */
public class KFCCola extends Cola {
    private float price;
    private String name;

    public KFCCola(float price, String name) {
        this.price = price;
        this.name = name;
    }

    @Override
    public float getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }
}
