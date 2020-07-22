package com.github.alonwang.transport.hello.message;

import com.github.alonwang.transport.core.protocol.AbstractRequest;
import com.github.alonwang.transport.core.protocol.MessageWrapper;
import com.github.alonwang.transport.protobuf.Hello;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.Data;

/**
 * @author alonwang
 * @date 2020/7/22 17:48
 * @detail
 */

@MessageWrapper(moduleId = 1, commandId = 1)
public class HelloRequest extends AbstractRequest {
    private Hello.HelloMessage helloMessage;

    @Override
    public void decode() throws InvalidProtocolBufferException {
        helloMessage = Hello.HelloMessage.parseFrom(body());
    }

    @Override
    public void encode() {
        setBody(helloMessage.toByteString());
    }

    public Hello.HelloMessage getHelloMessage() {
        return helloMessage;
    }

    public void setHelloMessage(Hello.HelloMessage helloMessage) {
        this.helloMessage = helloMessage;
    }
}
