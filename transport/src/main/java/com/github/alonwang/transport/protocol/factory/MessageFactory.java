package com.github.alonwang.transport.protocol.factory;

import com.github.alonwang.transport.protocol.AbstractMessage;
import com.github.alonwang.transport.protocol.MessageHeader;
import com.github.alonwang.transport.protocol.AbstractRequest;
import com.github.alonwang.transport.protocol.RequestHeader;
import com.github.alonwang.transport.core.MessageRegistry;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;

/**
 * @author alonwang
 * @date 2020/7/22 17:43
 * @detail
 */
@Slf4j
@Component
public class MessageFactory {
    @Autowired
    private MessageRegistry messageRegistry;

    public AbstractRequest createRequest(int moduleId, int commandId, Object body) {
        Class<? extends AbstractMessage> messageClazz = messageRegistry.getMessage(moduleId, commandId);
        Preconditions.checkNotNull(messageClazz, "moduleId({}),commandId({}) no relate Message", moduleId, commandId);
        try {
            Constructor<? extends AbstractMessage> constructor = messageClazz.getConstructor();
            AbstractMessage abstractMessage = constructor.newInstance();
            AbstractRequest abstractRequest = (AbstractRequest) abstractMessage;
            MessageHeader header = new RequestHeader(moduleId, commandId);
            abstractRequest.setHeader(header);
            abstractRequest.setBody(body);
            abstractRequest.decode();
            return abstractRequest;
        } catch (Exception e) {
            log.error("create request error", e);
        }
        return null;
    }
}