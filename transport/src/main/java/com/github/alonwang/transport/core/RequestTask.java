package com.github.alonwang.transport.core;

import com.github.alonwang.transport.protocol.AbstractRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @author alonwang
 * @date 2020/7/28 11:11
 * @detail
 */
@Slf4j
public class RequestTask implements MessageTask<Session> {
    private final MethodWrapper wrapper;
    private final AbstractRequest request;

    public RequestTask(MethodWrapper wrapper, AbstractRequest request) {
        this.wrapper = wrapper;
        this.request = request;
    }

    @Override
    public void execute(Session session) {
        try {
            wrapper.execute(session, request);
        } catch (Exception e) {
            log.error("session task execute error", e);
        }
    }
}
