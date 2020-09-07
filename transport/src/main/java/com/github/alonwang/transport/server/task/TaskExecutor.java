package com.github.alonwang.transport.server.task;

/**
 * 执行器
 *
 * @param <T> 约束T的范围，它必须既是组成Task的一部分，又是执行器
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
