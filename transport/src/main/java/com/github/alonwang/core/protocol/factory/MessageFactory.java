package com.github.alonwang.core.protocol.factory;

import com.github.alonwang.core.core.MessageRegistry;
import com.github.alonwang.core.protocol.Message;
import com.github.alonwang.core.protocol.MessageHeader;
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
            MessageHeader header = new MessageHeader(protocol.getModuleId(), protocol.getCommandId());
            message.setHeader(header);
            message.setData(protocol.getData());
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
        throw new RuntimeException();
    }

    /**
     * 解析{@link Message},生成{@link Protocol}
     *
     * @param msg
     * @return
     */
    public static Protocol toProtocol(Message<ByteString> msg) {
        var protocol = Protocol.newBuilder();
        protocol.setModuleId(msg.header().getModuleId()).setCommandId(msg.header().getCommandId());
        if (msg.header().getErrCode() != MessageHeader.ERR_CODE_SUCCESS) {
            protocol.setErrCode(msg.header().getErrCode());
            protocol.setErrMsg(msg.header().getErrMsg());
        } else {
            protocol.setData(msg.body());
        }
        return protocol.build();
    }
}
