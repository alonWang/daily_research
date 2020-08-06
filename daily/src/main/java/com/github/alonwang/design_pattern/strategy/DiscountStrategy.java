package com.github.alonwang.design_pattern.strategy;

/**
 * 打折策略
 */
public interface DiscountStrategy {
    /**
     * @param goodPrices 商品价格列表
     * @return 实付价格
     */
    double computeMoney(double[] goodPrices);
}
