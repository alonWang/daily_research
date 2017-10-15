package com.designpattern.singleton;

public class SingletonApplication {
	public static void main(String[] args) {
		
		AppConfig config = AppConfig.getAppConfig();
		System.out.println(config.show());
		
	}
}
