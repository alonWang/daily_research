package com.github.alonwang.design_pattern.strategy;

public class StrategyComputer implements ComputableStrategy {
	public double computeMoney(double[] a) {
		double sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum = sum + a[i] * 0.7;
		}
		return sum;
	}
}
