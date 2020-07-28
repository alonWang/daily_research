package com.github.alonwang.transport.core;

/**
 * 执行器,异步串行无锁化的核心接口
 * 为了拥有存储和串行执行任务的能力,所有的主体都需要实现此接口
 *
 * @author alonwang
 * @date 2020/7/27 15:47
 * @detail
 */
public interface TaskExecutor<T extends TaskExecutor<?>> {
    /**
     * 执行任务
     *
     * @param task
     */
    void execute(Task<T> task);
}
