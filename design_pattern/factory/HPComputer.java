package com.designpattern.factory;

public class HPComputer extends Computer {

	@Override
	public Brand getBrand() {
		// TODO Auto-generated method stub
		return new HPBrand();
	}

}
