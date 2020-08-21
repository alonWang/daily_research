package com.github.alonwang.handler;

import com.github.alonwang.register.HandlerRegister;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author alonwang
 * @date 2020/8/20 7:51 下午
 * @detail
 */
public abstract class AbstractHandlerRegister<I, P extends Handler<I>> implements HandlerRegister<I, P> {
    private final Map<I, P> handlerMap = new ConcurrentHashMap<>();

    @Override
    public Map<I, P> getHandlers() {
        return handlerMap;
    }

    @Override
    public P getHandler(I id) {
        return handlerMap.get(id);
    }

    @Override
    public boolean register(I id, P handler) {
        P oldHandler = handlerMap.putIfAbsent(id, handler);
        return oldHandler != handler;
    }
}
