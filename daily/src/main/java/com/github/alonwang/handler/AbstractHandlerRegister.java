package com.github.alonwang.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册器抽象实现
 *
 * @author alonwang
 * @date 2020/8/20 7:51 下午
 * @detail
 */
public abstract class AbstractHandlerRegister<I, H extends Handler<I>> implements HandlerRegister<I, H> {
    private final Map<I, H> handlerMap = new ConcurrentHashMap<>();

    @Override
    public Map<I, H> getHandlers() {
        return handlerMap;
    }

    @Override
    public H getHandler(I id) {
        return handlerMap.get(id);
    }

    @Override
    public void register(I id, H handler) {
        handlerMap.put(id, handler);

    }
}
