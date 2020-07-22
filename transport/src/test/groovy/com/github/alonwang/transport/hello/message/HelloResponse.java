package com.github.alonwang.transport.hello.message;

import com.github.alonwang.transport.core.protocol.AbstractResponse;
import com.github.alonwang.transport.core.protocol.MessageWrapper;
import com.github.alonwang.transport.protobuf.Hello;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author alonwang
 * @date 2020/7/22 18:05
 * @detail
 */
@MessageWrapper(moduleId = 1, commandId =2)
public class HelloResponse extends AbstractResponse {
    private Hello.MeToMessage meToMessage;

    @Override
    public void decode() throws InvalidProtocolBufferException {
        meToMessage = Hello.MeToMessage.parseFrom(body());
    }

    @Override
    public void encode() {
        setBody(meToMessage.toByteString());
    }
}
