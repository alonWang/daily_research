package com.github.alonwang.util.handler;

import com.github.alonwang.handler.Handler;

/**
 * @author alonwang
 * @date 2020/8/22 3:55 下午
 * @detail
 */
public interface WearableDeviceHandler extends Handler<WearableDeviceType> {
    /**
     * 穿戴
     */
    void wear(Object device);

    /**
     * 脱下
     */
    void takeoff(Object device);
}
