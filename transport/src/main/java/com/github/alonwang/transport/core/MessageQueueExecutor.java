package com.github.alonwang.transport.core;

/**
 * @author alonwang
 * @date 2020/7/27 15:47
 * @detail
 */
@FunctionalInterface
public interface MessageQueueExecutor<M extends MessageQueueExecutor<?>> {
    void addMessage(MessageTask<M> m);
}
