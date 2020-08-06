package com.github.alonwang.design_pattern.strategy;

import java.util.Arrays;

/**
 * 满减 满30减5元
 *
 * @author alonwang
 * @date 2020/8/5 12:09 下午
 * @detail
 */
public class DeductFiveIfReachThirtyStrategy implements DiscountStrategy {
    @Override
    public double computeMoney(double[] goodPrices) {
        double sum = Arrays.stream(goodPrices).sum();
        return sum > 30 ? sum - 5 : sum;
    }
}
