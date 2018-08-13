package com.github.alonwang.design_pattern.facede;

public class ClientServerFacade {
	
	private Camera camera;
	private Light light;
	private Sensor sensor;
	private Alarm alarm;
	
	public ClientServerFacade() {
		camera = new Camera();
		light = new Light();
		sensor = new Sensor();
		alarm = new Alarm();
	}
	public void startAll(){
		camera.start();
		light.start();
		sensor.start();
		alarm.start();
	}
	public void closeAll(){
		camera.close();
		light.close();
		sensor.close();
		alarm.close();
	}

}
