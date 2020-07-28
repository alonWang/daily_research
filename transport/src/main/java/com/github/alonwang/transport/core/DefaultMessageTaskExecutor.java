package com.github.alonwang.transport.core;

import io.netty.util.internal.PlatformDependent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author alonwang
 * @date 2020/7/27 16:25
 * @detail
 */
@Slf4j
public class DefaultMessageTaskExecutor<T extends MessageTaskExecutor<?>> implements Runnable, MessageTaskExecutor<T> {
    private static ExecutorService DEFAULT_EXECUTOR_SERVICE = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2, new CustomizableThreadFactory("MessageTask-Worker"));
    private volatile Thread current;
    private ExecutorService executorService;
    private Queue<MessageTask<T>> tasks = PlatformDependent.newMpscQueue();
    private AtomicInteger size = new AtomicInteger();

    public DefaultMessageTaskExecutor() {
        executorService = DEFAULT_EXECUTOR_SERVICE;
    }

    @Override
    public void addTask(MessageTask<T> task) {
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
            MessageTask<T> task = tasks.poll();
            if (task == null) {
                break;
            }
            try {
                //转型限制
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
