package com.github.alonwang.core.core;

import com.github.alonwang.core.server.task.JobExecutor;
import io.netty.util.internal.PlatformDependent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 执行器的默认实现,异步串行无锁化的核心
 * 为了拥有存储和串行执行任务的能力,所有的主体都需要实现此接口
 *
 * @author alonwang
 * @date 2020/7/27 16:25
 */
@Slf4j
public abstract class DefaultJobExecutor<T extends DefaultJobExecutor<?>> implements Runnable, JobExecutor<T> {
    private static final ExecutorService DEFAULT_EXECUTOR_SERVICE =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2,
                    new CustomizableThreadFactory("Task-Worker"));
    private final ExecutorService executorService;
    private final Queue<Job<T>> jobs = PlatformDependent.newMpscQueue();
    private final AtomicInteger size = new AtomicInteger();
    private volatile Thread current;

    public DefaultJobExecutor() {
        executorService = DEFAULT_EXECUTOR_SERVICE;
    }

    public DefaultJobExecutor(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void execute(Job<T> job) {
        jobs.add(job);
        int curSize = size.incrementAndGet();
        if (curSize == 1) {
            executorService.execute(this);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void run() {
        this.current = Thread.currentThread();
        while (true) {
            Job<T> job = jobs.poll();
            if (job == null) {
                break;
            }
            try {
                job.run((T) this);

            } catch (Exception e) {
                onExceptionCaught(job, e);
            }
            int curSize = size.decrementAndGet();
            if (curSize <= 0) {
                current = null;
                break;
            }
        }
    }

    protected void onExceptionCaught(Job<T> job, Exception e) {
        log.error(String.format("job(%s) run error", job.description()), e);
    }

    public boolean inThread() {
        return Thread.currentThread() == current;
    }
}
