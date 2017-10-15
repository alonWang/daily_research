package com.designpattern.factory;

public class Application {
	
	public static void main(String[] args) {
		
		Computer computer = new DellComputer();
		
		computer = new HPComputer();
		
		computer = new LenovoComputer();
		
		computer = new AcerComputer();
	
		
	}

}
