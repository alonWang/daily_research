package com.github.alonwang.design_pattern.strategy;

public class Application {

	public static void main(String[] arsg) {
		Shopping shop = new Shopping();
		shop.setStrategy(new StrategyComputer());
		double[] computerBooks = { 10, 25, 33 };
		double num1 = shop.getMoney(computerBooks);

		shop.setStrategy(new StrategyEnglish());
		double[] EnglishBooks = { 26, 34, 45 };
		double num2 = shop.getMoney(EnglishBooks);

		System.out.println("用户支付的金额为" + (num1 + num2));
	}
}
