package com.designpattern.factory;

public abstract class Computer {
		
	Computer() {
		System.out.println("生产了一台" + getBrand().brand + "品牌的笔记本");
	}
	
	public abstract Brand getBrand();
}
