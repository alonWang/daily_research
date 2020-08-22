package com.github.alonwang.handler;

import java.util.Map;

/**
 * @author alonwang
 * @date 2020/8/20 8:01 下午
 * @detail
 */
public interface HandlerRegister<I, H> {
    /**
     * 获取所有handler
     *
     * @return
     */
    Map<I, H> getHandlers();

    /**
     * 根据标识获取handler
     *
     * @return
     */
    H getHandler(I id);

    /**
     * 注册handler
     *
     * @param id
     * @param handler
     */
    void register(I id, H handler);

}
