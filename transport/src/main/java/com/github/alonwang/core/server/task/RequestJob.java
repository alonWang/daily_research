package com.github.alonwang.core.server.task;

import com.github.alonwang.core.protocol.Request;
import com.github.alonwang.core.server.task.JobExecutor.Job;
import lombok.extern.slf4j.Slf4j;

/**
 * 请求(名词)任务
 *
 * @author alonwang
 * @date 2020/7/28 11:11
 * @detail
 */
@Slf4j
public class RequestJob implements Job<Session> {
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
        } catch (Exception e) {
            log.error("session task execute error", e);
        }
    }
}
