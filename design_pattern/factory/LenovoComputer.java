package com.designpattern.factory;

public class LenovoComputer extends Computer {

	@Override
	public Brand getBrand() {
		// TODO Auto-generated method stub
		return new LenovoBrand();
	}

}
