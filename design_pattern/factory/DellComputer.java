package com.designpattern.factory;

public class DellComputer extends Computer {

	@Override
	public Brand getBrand() {
		// TODO Auto-generated method stub
		return new AcerBrand();
	}

}
