package com.github.alonwang.logic.hello.message;

import com.github.alonwang.core.protocol.AbstractResponse;
import com.github.alonwang.core.protocol.MessageWrapper;
import com.github.alonwang.logic.core.CommandIds;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.github.alonwang.logic.protobuf.Hello.MeToMessage;

/**
 * @author alonwang
 * @date 2020/7/22 18:05
 * @detail
 */
@EqualsAndHashCode(callSuper = true)
@Data
@MessageWrapper(moduleId = CommandIds.HelloModule, commandId = CommandIds.Hello.meTo)
public class HelloResponse extends AbstractResponse {
    private MeToMessage message;

    @Override
    public void decode() throws InvalidProtocolBufferException {
        message = MeToMessage.parseFrom(body());
    }

    @Override
    public void encode() {
        setBody(message.toByteString());
    }

    public MeToMessage getMessage() {
        return message;
    }

    public void setMessage(MeToMessage message) {
        this.message = message;
    }
}
