package com.designpattern.facede;

public class Security {
	
	private String name;
	private ClientServerFacade clientFacade;
	
	public Security(String name, ClientServerFacade clientFacade) {
		this.name = name;
		this.clientFacade = clientFacade;
		System.out.println(name + "正在值班");
	}
	
	public ClientServerFacade control() {
		return this.clientFacade;
	}
	

}
