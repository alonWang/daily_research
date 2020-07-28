package com.github.alonwang.transport.service;

import com.github.alonwang.transport.core.MessageRegistry;
import com.github.alonwang.transport.core.MethodWrapper;
import com.github.alonwang.transport.core.Session;
import com.github.alonwang.transport.core.RequestTask;
import com.github.alonwang.transport.protocol.AbstractRequest;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author alonwang
 * @date 2020/7/24 15:15
 * @detail
 */
@Slf4j
@Component
public class RequestDispatchService {
    @Autowired
    private MessageRegistry messageRegistry;

    public void dispatch(Session session, AbstractRequest request) {
        MethodWrapper wrapper = messageRegistry.getWrapper(request.header().getModuleId(), request.header().getCommandId());
        Preconditions.checkNotNull(wrapper);
        session.addTask(new RequestTask(wrapper, request));

    }
}
