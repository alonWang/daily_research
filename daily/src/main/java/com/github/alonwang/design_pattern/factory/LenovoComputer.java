package com.github.alonwang.design_pattern.factory;

public class LenovoComputer extends Computer {

	@Override
	public Brand getBrand() {
		// TODO Auto-generated method stub
		return new LenovoBrand();
	}

}
