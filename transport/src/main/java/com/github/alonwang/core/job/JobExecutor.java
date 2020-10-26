package com.github.alonwang.core.job;

import java.util.concurrent.Executor;

/**
 * 任务执行器, 功能与{@link Executor}类似
 * 结合{@link Job}提供异步串行能力
 * eg.
 *
 * @param <T>
 * @author alonwang
 * @date 2020/7/27 15:47
 * @detail
 */
public interface JobExecutor<T extends JobExecutor<?>> {
    /**
     * 与{@link Executor#execute(java.lang.Runnable)}类似
     *
     * @param job
     */
    void execute(Job<T> job);

    /**
     * 任务接口,与{@link Runnable}类似
     *
     * @param <E>
     */
    interface Job<E extends JobExecutor<?>> {
        /**
         * 运行任务
         *
         * @param e
         */
        void run(E e);

        /**
         * 任务描述
         *
         * @return
         */
        default String description() {
            return this.getClass().getSimpleName();
        }
    }
}
