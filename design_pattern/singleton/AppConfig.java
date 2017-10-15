package com.designpattern.singleton;

public class AppConfig {
	
	private static AppConfig uniqueAppConfig;
	private AppConfig() {
		uniqueAppConfig = this;
	}
	
	public static synchronized AppConfig getAppConfig() {
		if(uniqueAppConfig == null) {
			uniqueAppConfig = new AppConfig();
		}
		return uniqueAppConfig;
	}
	
	public String show() {
		String s = "配置文件for 王";
		return s;
	}

}
