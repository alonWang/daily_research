package com.github.alonwang.design_pattern.strategy;

/**
 * 白送策略,每件只收1元
 */
public class AlmostFreeStrategy implements DiscountStrategy {
    @Override
    public double computeMoney(double[] goodPrices) {
        return goodPrices.length * 1;
    }
}
