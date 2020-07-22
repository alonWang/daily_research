package com.github.alonwang.transport.hello.message;

import com.github.alonwang.transport.core.protocol.AbstractResponse;
import com.github.alonwang.transport.core.protocol.MessageWrapper;
import com.github.alonwang.transport.protobuf.Hello;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.EqualsAndHashCode;

/**
 * @author alonwang
 * @date 2020/7/22 18:05
 * @detail
 */
@EqualsAndHashCode(callSuper = true)
@MessageWrapper(moduleId = 1, commandId =2)
public class HelloResponse extends AbstractResponse {
    private Hello.MeToMessage data;

    @Override
    public void decode() throws InvalidProtocolBufferException {
        data = Hello.MeToMessage.parseFrom(body());
    }

    @Override
    public void encode() {
        setBody(data.toByteString());
    }

    public Hello.MeToMessage getData() {
        return data;
    }

    public void setData(Hello.MeToMessage data) {
        this.data = data;
    }
}
