package com.designpattern.abstractfactory;

/**
 * Created by dvWang on 2017/5/18.
 */
public class KFCHamburg extends Hamburg {
    private float price;
    private String name;

    public KFCHamburg(float price, String name) {
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
