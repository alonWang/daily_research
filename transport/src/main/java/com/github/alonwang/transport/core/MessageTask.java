package com.github.alonwang.transport.core;

/**
 * 消息任务,
 *
 * @author alonwang
 * @date 2020/7/27 16:17
 * @detail
 */
@FunctionalInterface
public interface MessageTask<T extends MessageTaskExecutor<?>> {
    /**
     * 执行任务
     *
     * @param t 主体对象
     */
    void execute(T t);
}
