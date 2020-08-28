package com.github.alonwang.transport.protocol.factory;

import com.github.alonwang.transport.core.Context;
import com.github.alonwang.transport.protocol.*;
import com.google.common.base.Preconditions;
import com.google.protobuf.ByteString;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;

/**
 * @author alonwang
 * @date 2020/7/22 17:43
 * @detail
 */
@Slf4j
public class MessageFactory {


    public static <T extends AbstractRequest> T createRequest(int moduleId, int commandId, ByteString body) {
        Class<? extends AbstractMessage> messageClazz = Context.getMessageRegistry().getMessage(moduleId, commandId);
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

    public static <T extends AbstractResponse> T createResponse(int moduleId, int commandId) {
        Class<? extends AbstractMessage> messageClazz = Context.getMessageRegistry().getMessage(moduleId, commandId);
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
