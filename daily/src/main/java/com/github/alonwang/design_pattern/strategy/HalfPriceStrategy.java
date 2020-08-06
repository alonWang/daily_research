package com.github.alonwang.design_pattern.strategy;

import java.util.Arrays;

/**
 * 打折策略，只收半价
 */
public class HalfPriceStrategy implements DiscountStrategy {
    @Override
    public double computeMoney(double[] goodPrices) {
        return Arrays.stream(goodPrices).sum() * 0.5;
    }
}
