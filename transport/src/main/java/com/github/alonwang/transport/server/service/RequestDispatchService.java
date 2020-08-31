package com.github.alonwang.transport.server.service;

import com.github.alonwang.transport.core.MessageRegistry;
import com.github.alonwang.transport.protocol.AbstractRequest;
import com.github.alonwang.transport.server.task.MessageTask;
import com.github.alonwang.transport.server.task.MethodWrapper;
import com.github.alonwang.transport.server.task.User;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 分发器，将消息分发到对应的方法上执行
 *
 * @author alonwang
 * @date 2020/7/24 15:15
 * @detail
 */
@Slf4j
@Component
public class RequestDispatchService {
    @Autowired
    private MessageRegistry messageRegistry;

    public void dispatch(User user, AbstractRequest request) {
        MethodWrapper wrapper = messageRegistry.getWrapper(request.header().getModuleId(), request.header().getCommandId());
        Preconditions.checkNotNull(wrapper);
        user.execute(new MessageTask(wrapper, request));

    }
}
