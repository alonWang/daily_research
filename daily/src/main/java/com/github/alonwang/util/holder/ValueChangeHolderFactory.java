package com.github.alonwang.util.holder;

/**
 * @author alonwang
 * @date 2020/4/28 19:48
 * @description
 * @detail
 */
public class ValueChangeHolderFactory {
    public static ValueChangeHolder create() {
        return new DefaultValueChangeHolder();
    }

    public static ValueChangeHolder createPositiveHolder() {
        return new PositiveValueChangeHolder();
    }

    public static ValueChangeHolder createNegativeHolder() {
        return new NegativeValueChangeHolder();
    }

    public static void main(String[] args) {
        ValueChangeHolder defaultHolder=create();
        defaultHolder.setNew(1000);
        defaultHolder.setOld(500);
        defaultHolder.freeze();
        System.out.println("defaultHolder: "+defaultHolder);
        ValueChangeHolder positiveHolder=createPositiveHolder();
        positiveHolder.setOld(1000);
        positiveHolder.setChange(10);
        positiveHolder.freeze();
        System.out.println("positiveHolder: "+positiveHolder);
        ValueChangeHolder negativeHolder=createNegativeHolder();
        negativeHolder.setNew(1000);
        negativeHolder.setChange(-10);
        negativeHolder.freeze();
        System.out.println("negativeHolder: "+negativeHolder);

    }
}
