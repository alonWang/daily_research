package com.github.alonwang.core.protocol.factory;

import com.github.alonwang.core.core.MessageRegistry;
import com.github.alonwang.core.exception.NoSuchHeaderException;
import com.github.alonwang.core.protocol.Message;
import com.github.alonwang.core.protocol.MessageHeader;
import com.github.alonwang.core.protocol.Request;
import com.github.alonwang.core.protocol.RequestHeader;
import com.github.alonwang.core.protocol.Response;
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

    /**
     * 解析请求,生成协议包
     *
     * @param moduleId
     * @param commandId
     * @param body
     * @param <T>
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public <T extends Request> T parseRequest(int moduleId, int commandId, ByteString body) {
        Class<? extends Message> messageClazz = messageRegistry.getMessage(moduleId, commandId);
        Preconditions.checkNotNull(messageClazz, "moduleId({}),commandId({}) no relate Message", moduleId, commandId);
        Preconditions.checkArgument(Request.class.isAssignableFrom(messageClazz));
        try {
            Constructor<? extends Message> constructor = messageClazz.getConstructor();
            Message message = constructor.newInstance();
            Request abstractRequest = (Request) message;
            MessageHeader header = new RequestHeader(moduleId, commandId);
            abstractRequest.setHeader(header);
            abstractRequest.setData(body);
            abstractRequest.decode();
            return (T) abstractRequest;
        } catch (Exception e) {
            log.error("create request error", e);
        }
        return null;
    }

    /**
     * 创建一个协议包
     *
     * @param moduleId  协议包的模块id
     * @param commandId 协议包的命令id
     * @param <T>       协议包表示类的类型
     * @return 协议包
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public <T extends Message> T createMessage(int moduleId, int commandId) {
        Class<? extends Message> messageClazz = messageRegistry.getMessage(moduleId, commandId);
        Preconditions.checkNotNull(messageClazz, "moduleId({}),commandId({}) no relate Message", moduleId, commandId);
        try {
            Constructor<? extends Message> constructor = messageClazz.getConstructor();
            Message message = constructor.newInstance();

            MessageHeader header = createHeader(messageClazz);
            header.setModuleId(moduleId);
            header.setCommandId(commandId);
            message.setHeader(header);
            return (T) message;
        } catch (Exception e) {
            log.error("create request error", e);
        }
        throw new RuntimeException();
    }

    /**
     * 实例化header
     *
     * @param clazz 消息表示的类,继承自{@link Message}
     * @return {@link RequestHeader}或{@link ResponseHeader}
     * @throws NoSuchHeaderException 没有对应类型的header
     */
    @SuppressWarnings("rawtypes")
    private MessageHeader createHeader(Class<? extends Message> clazz) {
        if (Request.class.isAssignableFrom(clazz)) {
            return new RequestHeader();
        }
        if (Response.class.isAssignableFrom(clazz)) {
            return new ResponseHeader();
        }
        throw new NoSuchHeaderException(String.format("class(%s) no related header", clazz));
    }
}
