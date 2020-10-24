package com.github.alonwang.core.server.task;

import com.github.alonwang.core.protocol.Request;
import com.github.alonwang.core.server.task.JobExecutor.Job;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

/**
 * 请求(名词)任务
 *
 * @author alonwang
 * @date 2020/7/28 11:11
 * @detail
 */
@Slf4j
public class RequestJob implements Job<Session> {
    /**
     * 封装方法的参数列表为 (Session,Request)
     */
    private final MethodWrapper wrapper;
    private final Request request;

    public RequestJob(MethodWrapper wrapper, Request request) {
        this.wrapper = wrapper;
        this.request = request;
    }

    @Override
    public void run(Session session) {
        try {
            wrapper.invoke(session, request);
        } catch (InvocationTargetException|IllegalAccessException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException) cause;
            }
            log.error("method invoke error", e);
        }

    }

    @Override
    public String description() {
        return wrapper.getMethod().toString();
    }
}
