package com.github.alonwang.logic.hello.message;

import com.github.alonwang.core.protocol.AbstractRequest;
import com.github.alonwang.core.protocol.MessageWrapper;
import com.github.alonwang.logic.core.CommandIds;
import com.github.alonwang.logic.protobuf.Hello;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author alonwang
 * @date 2020/7/22 17:48
 * @detail
 */

@MessageWrapper(moduleId = CommandIds.HelloModule, commandId = CommandIds.Hello.hello)
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
