package com.github.alonwang.util.handler;

import org.springframework.stereotype.Component;

/**
 * @author alonwang
 * @date 2020/8/22 4:17 下午
 * @detail
 */
@Component
public class WatchHandler implements WearableDeviceHandler {
    @Override
    public void wear(Object device) {
        System.out.printf("戴上%s有点麻烦%n", device);
    }

    @Override
    public void takeoff(Object device) {
        System.out.printf("脱下%s也有点麻烦%n", device);
    }

    @Override
    public WearableDeviceType id() {
        return WearableDeviceType.Watch;
    }
}
