package com.github.alonwang.transport.core;

import com.github.alonwang.transport.protocol.AbstractRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @author alonwang
 * @date 2020/7/28 11:11
 * @detail
 */
@Slf4j
public class MessageTask implements Task<User> {
    private final MethodWrapper wrapper;
    private final AbstractRequest request;

    public MessageTask(MethodWrapper wrapper, AbstractRequest request) {
        this.wrapper = wrapper;
        this.request = request;
    }

    @Override
    public void execute(User user) {
        try {
            wrapper.invoke(user, request);
        } catch (Exception e) {
            log.error("session task execute error", e);
        }
    }
}
