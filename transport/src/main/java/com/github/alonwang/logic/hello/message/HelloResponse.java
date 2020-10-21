package com.github.alonwang.logic.hello.message;

import com.github.alonwang.core.protocol.MessageId;
import com.github.alonwang.core.protocol.Response;
import com.github.alonwang.logic.core.MessageIds;
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
@MessageId(moduleId = MessageIds.HelloModule, commandId = MessageIds.Hello.meTo)
public class HelloResponse extends Response {
    private MeToMessage message;

    @Override
    public void decode() throws InvalidProtocolBufferException {
        message = MeToMessage.parseFrom(body());
    }

    @Override
    public void encode() {
        setData(message.toByteString());
    }

    public MeToMessage getMessage() {
        return message;
    }

    public void setMessage(MeToMessage message) {
        this.message = message;
    }
}
