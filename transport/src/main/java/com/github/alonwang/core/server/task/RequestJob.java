package com.github.alonwang.core.server.task;

import com.github.alonwang.core.exception.BusinessException;
import com.github.alonwang.core.protocol.Message;
import com.github.alonwang.core.protocol.Request;
import com.github.alonwang.core.server.task.JobExecutor.Job;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

/**
 * 请求(名词)任务
 * <p>
 * 如果执行的方法有返回值
 *
 * @author alonwang
 * @date 2020/7/28 11:11
 */
@Slf4j
public class RequestJob implements Job<Session> {
    /**
     * 网络请求对应的处理方法
     */
    private final MethodWrapper wrapper;
    private final Request request;

    public RequestJob(MethodWrapper wrapper, Request request) {
        this.wrapper = wrapper;
        this.request = request;
    }

    /**
     * 执行请求对应的处理逻辑
     *
     *
     * @param session
     */
    @Override
    public void run(Session session) {
        try {
            Object retVal = wrapper.invoke(session, request);
            if (retVal != null && Message.class.isAssignableFrom(wrapper.returnType())) {

            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            Throwable cause = e.getCause();
            if (cause instanceof BusinessException) {
                throw (BusinessException) cause;
            }
            log.error("request job invoke error", e);
        }
        //TODO 请求响应封装


    }

    @Override
    public String description() {
        return wrapper.getMethod().toString();
    }
}
