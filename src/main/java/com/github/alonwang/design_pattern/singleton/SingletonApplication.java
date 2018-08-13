package com.github.alonwang.design_pattern.singleton;

public class SingletonApplication {
	public static void main(String[] args) {
		
		AppConfig config = AppConfig.getAppConfig();
		System.out.println(config.show());
		
	}
}
