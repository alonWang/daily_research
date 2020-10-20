package com.github.alonwang.core.protocol.factory;

import com.github.alonwang.core.core.MessageRegistry;
import com.github.alonwang.core.protocol.AbstractMessage;
import com.github.alonwang.core.protocol.AbstractRequest;
import com.github.alonwang.core.protocol.AbstractResponse;
import com.github.alonwang.core.protocol.MessageHeader;
import com.github.alonwang.core.protocol.RequestHeader;
import com.github.alonwang.core.protocol.ResponseHeader;
import com.google.common.base.Preconditions;
import com.google.protobuf.ByteString;
import lombok.extern.slf4j.Slf4j;
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
    private MessageRegistry messageRegistry;

    public MessageFactory(MessageRegistry messageRegistry) {
        this.messageRegistry = messageRegistry;
    }

    public <T extends AbstractRequest> T createRequest(int moduleId, int commandId, ByteString body) {
        Class<? extends AbstractMessage> messageClazz = messageRegistry.getMessage(moduleId, commandId);
        Preconditions.checkNotNull(messageClazz, "moduleId({}),commandId({}) no relate Message", moduleId, commandId);
        Preconditions.checkArgument(AbstractRequest.class.isAssignableFrom(messageClazz));
        try {
            Constructor<? extends AbstractMessage> constructor = messageClazz.getConstructor();
            AbstractMessage abstractMessage = constructor.newInstance();
            AbstractRequest abstractRequest = (AbstractRequest) abstractMessage;
            MessageHeader header = new RequestHeader(moduleId, commandId);
            abstractRequest.setHeader(header);
            abstractRequest.setBody(body);
            abstractRequest.decode();
            return (T) abstractRequest;
        } catch (Exception e) {
            log.error("create request error", e);
        }
        return null;
    }

    public <T extends AbstractResponse> T createResponse(int moduleId, int commandId) {
        Class<? extends AbstractMessage> messageClazz = messageRegistry.getMessage(moduleId, commandId);
        Preconditions.checkNotNull(messageClazz, "moduleId({}),commandId({}) no relate Message", moduleId, commandId);
        Preconditions.checkArgument(AbstractResponse.class.isAssignableFrom(messageClazz));
        try {
            Constructor<? extends AbstractMessage> constructor = messageClazz.getConstructor();
            AbstractMessage abstractMessage = constructor.newInstance();
            AbstractResponse abstractResponse = (AbstractResponse) abstractMessage;
            MessageHeader header = new ResponseHeader(moduleId, commandId);
            abstractResponse.setHeader(header);
            return (T) abstractResponse;
        } catch (Exception e) {
            log.error("create request error", e);
        }
        return null;
    }
}
