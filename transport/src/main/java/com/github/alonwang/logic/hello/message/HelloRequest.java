package com.github.alonwang.logic.hello.message;

import com.github.alonwang.core.protocol.MessageId;
import com.github.alonwang.core.protocol.Request;
import com.github.alonwang.logic.core.MessageIds;
import com.github.alonwang.logic.protobuf.Hello;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author alonwang
 * @date 2020/7/22 17:48
 * @detail
 */

@MessageId(moduleId = MessageIds.HelloModule, commandId = MessageIds.Hello.hello)
public class HelloRequest extends Request {
    private Hello.HelloMessage body;

    @Override
    public void decode() throws InvalidProtocolBufferException {
        body = Hello.HelloMessage.parseFrom(body());
    }

    @Override
    public void encode() {
        setData(body.toByteString());
    }

    public Hello.HelloMessage getBody() {
        return body;
    }

    public void setBody(Hello.HelloMessage body) {
        this.body = body;
    }
}
