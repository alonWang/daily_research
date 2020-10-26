package com.github.alonwang.core.job;

import com.github.alonwang.core.exception.BusinessException;
import com.github.alonwang.core.job.JobExecutor.Job;
import lombok.extern.slf4j.Slf4j;

/**
 * Job代理,提供异常日志记录功能
 *
 * @author alonwang
 * @date 2020/10/24 12:31
 */
@Slf4j
public class JobExceptionLogProxy<E extends JobExecutor<?>> implements Job<E> {
    private final Job<E> target;

    public JobExceptionLogProxy(Job<E> target) {
        this.target = target;
    }

    @Override
    public void run(E e) {
        try {
            target.run(e);
        } catch (Exception exception) {

            if (exception instanceof BusinessException) {
                BusinessException be = (BusinessException) exception;
                log.info(String.format("business exception occur: errCode(%s)\ntarget(%s)\nmsg(%s)",
                        be.getErrCode(), e, target.description()), be);
            } else {
                log.warn(String.format("job run error: target(%s)", e),
                        exception);
            }


        }
    }

    @Override
    public String description() {
        return target.getClass().getSimpleName();
    }
}
