package com.github.alonwang.util.handler;

import org.springframework.stereotype.Component;

/**
 * @author alonwang
 * @date 2020/8/22 4:03 下午
 * @detail
 */
@Component
public class BraceletHandler implements WearableDeviceHandler {
    @Override
    public void wear(Object device) {
        System.out.printf("戴上%s，很轻便%n", device);
    }

    @Override
    public void takeoff(Object device) {
        System.out.printf("脱下%s,没感觉到差别%n", device);
    }

    @Override
    public WearableDeviceType id() {
        return WearableDeviceType.Bracelet;
    }
}
