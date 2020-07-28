package com.github.alonwang.transport.core;

import io.netty.util.internal.PlatformDependent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 执行器的默认实现,主体可直接继承此类.
 *
 * @author alonwang
 * @date 2020/7/27 16:25
 * @detail DefaultTaskExecutor自身有一个任务队列, 用来存储任务.当添加任务后, 如果发现添加后正好有一个任务, 就会启动run逻辑.
 * DefaultTaskExecutor实现了Runnable接口.它的run()逻辑就是一直执行任务队列里的任务.
 */
@Slf4j
public class DefaultTaskExecutor<T extends TaskExecutor<?>> implements Runnable, TaskExecutor<T> {
    private static final ExecutorService DEFAULT_EXECUTOR_SERVICE = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2, new CustomizableThreadFactory("MessageTask-Worker"));
    private final ExecutorService executorService;
    private final Queue<Task<T>> tasks = PlatformDependent.newMpscQueue();
    private final AtomicInteger size = new AtomicInteger();
    private volatile Thread current;

    public DefaultTaskExecutor() {
        executorService = DEFAULT_EXECUTOR_SERVICE;
    }

    @Override
    public void execute(Task<T> task) {
        tasks.add(task);
        int curSize = size.incrementAndGet();
        if (curSize == 1) {
            executorService.execute(this);
        }
    }

    @Override
    public void run() {
        this.current = Thread.currentThread();
        while (true) {
            Task<T> task = tasks.poll();
            if (task == null) {
                break;
            }
            try {
                task.execute((T) this);
            } catch (Exception e) {
                log.error("task execute error", e);
            }
            int curSize = size.decrementAndGet();
            if (curSize <= 0) {
                break;
            }
        }
    }
}
