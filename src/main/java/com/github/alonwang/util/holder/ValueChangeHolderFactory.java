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


}
