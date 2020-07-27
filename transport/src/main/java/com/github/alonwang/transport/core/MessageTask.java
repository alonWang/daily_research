package com.github.alonwang.transport.core;

/**
 * @author alonwang
 * @date 2020/7/27 16:17
 * @detail
 */
@FunctionalInterface
public interface MessageTask<M extends MessageQueueExecutor<?>> {
    void execute(M m);
}
