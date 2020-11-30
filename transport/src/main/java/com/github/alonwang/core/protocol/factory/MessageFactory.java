package com.github.alonwang.core.protocol.factory;

import com.github.alonwang.core.protocol.message.MessageRegistry;
import com.github.alonwang.core.exception.GlobalErrorCode;
import com.github.alonwang.core.protocol.message.Message;
import com.github.alonwang.core.protocol.message.MessageHeader;
import com.github.alonwang.core.protocol.annotation.MessageId;
import com.github.alonwang.core.protocol.protobuf.Base;
import com.github.alonwang.core.protocol.protobuf.Base.Protocol;
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
     * 解析协议包,生成消息
     *
     * @param protocol
     * @param <T>
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public <T extends Message> T toMessage(Protocol protocol) {
        Class<? extends Message> messageClazz = messageRegistry.getMessage(protocol.getModuleId(),
                protocol.getCommandId());
        Preconditions.checkNotNull(messageClazz, "moduleId({}),commandId({}) no relate Message",
                protocol.getModuleId(), protocol.getCommandId());
        try {
            Constructor<? extends Message> constructor = messageClazz.getConstructor();
            Message message = constructor.newInstance();
            MessageHeader header = new MessageHeader(protocol.getModuleId(), protocol.getCommandId(),
                    protocol.getErrCode(), protocol.getErrMsg());
            message.setHeader(header);
            message.setPayload(protocol.getPayload());
            message.decode();
            return (T) message;
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
            MessageHeader header = new MessageHeader(moduleId, commandId);
            message.setHeader(header);
            return (T) message;
        } catch (Exception e) {
            log.error("create request error", e);
        }
        //TODO 更换为特定异常
        throw new RuntimeException();
    }

    public <T extends Message<?>> T createMessage(Class<?> clazz) {
        if (!Message.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException(String.format("can't create message instance of %s. it's not sub class" +
                    " of Message", clazz));
        }

        MessageId messageIdAnnotation = clazz.getAnnotation(MessageId.class);
        if (messageIdAnnotation == null) {
            throw new IllegalArgumentException("can't create message instance of" + clazz.getSimpleName() + ",it has " +
                    "no annotation @MessageId");
        }

        return createMessage(messageIdAnnotation.moduleId(), messageIdAnnotation.commandId());
    }

    /**
     * 解析{@link Message},生成{@link Protocol}
     *
     * @param msg
     * @return
     */
    public static Protocol toProtocol(Message<ByteString> msg) {
        Protocol.Builder  builder = Protocol.newBuilder();
        builder.setModuleId(msg.getHeader().getModuleId()).setCommandId(msg.getHeader().getCommandId());
        if (msg.getHeader().getErrCode() != GlobalErrorCode.SUCCESS) {
            builder.setErrCode(msg.getHeader().getErrCode());
            builder.setErrMsg(msg.getHeader().getErrMsg());
        } else {
            builder.setPayload(msg.getPayload());
        }
        return builder.build();
    }
}
