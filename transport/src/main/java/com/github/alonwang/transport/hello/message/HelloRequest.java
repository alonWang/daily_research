package com.github.alonwang.transport.hello.message;

import com.github.alonwang.transport.protocol.AbstractRequest;
import com.github.alonwang.transport.protocol.MessageWrapper;
import com.github.alonwang.transport.protobuf.Hello;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author alonwang
 * @date 2020/7/22 17:48
 * @detail
 */

@MessageWrapper(moduleId = 1, commandId = 1)
public class HelloRequest extends AbstractRequest {
    private Hello.HelloMessage body;

    @Override
    public void decode() throws InvalidProtocolBufferException {
        body = Hello.HelloMessage.parseFrom(body());
    }

    @Override
    public void encode() {
        setBody(body.toByteString());
    }

    public Hello.HelloMessage getBody() {
        return body;
    }

    public void setBody(Hello.HelloMessage body) {
        this.body = body;
    }
}
