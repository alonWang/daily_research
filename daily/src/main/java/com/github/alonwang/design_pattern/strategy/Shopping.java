package com.github.alonwang.design_pattern.strategy;

/**
 * 商店，会计算购买商品需要多少钱
 */
public class Shopping {
	private DiscountStrategy strategy;

	public void setStrategy(DiscountStrategy strategy) {
		this.strategy = strategy;
	}

	public double getMoney(double[] a) {
		if (strategy != null)
			return strategy.computeMoney(a);
		else
			return 0;
	}
}
