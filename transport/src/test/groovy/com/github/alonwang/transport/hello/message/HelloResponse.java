package com.github.alonwang.transport.hello.message;

import com.github.alonwang.transport.protobuf.Hello;
import com.github.alonwang.transport.protocol.AbstractResponse;
import com.github.alonwang.transport.protocol.MessageWrapper;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.EqualsAndHashCode;

/**
 * @author alonwang
 * @date 2020/7/22 18:05
 * @detail
 */
@EqualsAndHashCode(callSuper = true)
@MessageWrapper(moduleId = 1, commandId = 2)
public class HelloResponse extends AbstractResponse {
    private Hello.MeToMessage message;

    @Override
    public void decode() throws InvalidProtocolBufferException {
        message = Hello.MeToMessage.parseFrom(body());
    }

    @Override
    public void encode() {
        setBody(message.toByteString());
    }

    public Hello.MeToMessage getMessage() {
        return message;
    }

    public void setMessage(Hello.MeToMessage message) {
        this.message = message;
    }
}
