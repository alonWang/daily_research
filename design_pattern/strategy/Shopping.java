package com.designpattern.strategy;

public class Shopping {
	ComputableStrategy strategy;

	public void setStrategy(ComputableStrategy strategy) {
		this.strategy = strategy;
	}

	public double getMoney(double[] a) {
		if (strategy != null)
			return strategy.computeMoney(a);
		else
			return 0;
	}
}
