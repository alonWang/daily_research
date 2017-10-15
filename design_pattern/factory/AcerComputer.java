package com.designpattern.factory;

public class AcerComputer extends Computer {

	@Override
	public Brand getBrand() {
		// TODO Auto-generated method stub
		return new DellBrand();
	}

}
