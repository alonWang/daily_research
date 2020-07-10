package com.github.alonwang.design_pattern.facede;

public class Application {
	
	public static void main(String[] args) {
		
		ClientServerFacade clientFacade = new ClientServerFacade();
		Security baoAn = new Security("Bilibili",clientFacade);
		baoAn.control().startAll();

		System.out.println();
		baoAn.control().closeAll();
		
	}

}
