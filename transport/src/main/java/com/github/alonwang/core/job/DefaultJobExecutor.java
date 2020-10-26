package com.github.alonwang.core.job;

import io.netty.util.internal.PlatformDependent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 执行器的默认实现
 * <p>
 * {@link DefaultJobExecutor#execute(Job)}和{@link DefaultJobExecutor#run()}结合实现异步串行.
 *
 * @author alonwang
 * @date 2020/7/27 16:25
 */
@Slf4j
public abstract class DefaultJobExecutor<T extends DefaultJobExecutor<?>> implements Runnable, JobExecutor<T> {
    /**
     * 默认执行线程池
     */
    private static final ExecutorService DEFAULT_EXECUTOR_SERVICE =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2,
                    new CustomizableThreadFactory("Task-Worker"));
    /**
     * 执行线程池
     */
    private final ExecutorService executorService;
    /**
     * 任务队列
     * {@link DefaultJobExecutor#execute(Job)}添加任务到队列
     * {@link DefaultJobExecutor#run()}消耗队列任务
     */
    private final Queue<Job<T>> jobs = PlatformDependent.newMpscQueue();
    /**
     * {@link jobs} 队列当前的任务数量
     * {@link Queue#size()}性能消耗较大,因此在外部维护任务数量
     */
    private final AtomicInteger size = new AtomicInteger();
    /**
     * 执行任务时绑定的线程,未执行任务时无绑定线程
     * <p>
     * volatile确保其修改后其他线程立刻可见
     */
    private volatile Thread current;

    public DefaultJobExecutor() {
        executorService = DEFAULT_EXECUTOR_SERVICE;
    }

    public DefaultJobExecutor(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void execute(Job<T> job) {
        // 添加到队列,如果队列当前只有这一个任务,启动任务消耗逻辑:丢到线程池中执行
        jobs.add(job);
        int curSize = size.incrementAndGet();
        if (curSize == 1) {
            executorService.execute(this);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void run() {
        // 持续消耗队列任务直到没有新任务.开始时绑定线程,结束时解绑线程
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

    /**
     * 当前执行线程是否为{@link current}线程
     * 用来优化同一线程下的执行效率: 如果为真,表示为同一线程,可以直接执行.
     * 否则需要封装为任务
     *
     * @return
     */
    public boolean inThread() {
        return Thread.currentThread() == current;
    }
}
