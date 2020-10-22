package com.github.alonwang.logic.hello.message;

import com.github.alonwang.core.protocol.BodyField;
import com.github.alonwang.core.protocol.MessageId;
import com.github.alonwang.core.protocol.Request;
import com.github.alonwang.logic.core.MessageIds;
import com.github.alonwang.logic.protobuf.Hello.HelloMessage;
import lombok.Getter;
import lombok.Setter;

/**
 * @author alonwang
 * @date 2020/7/22 17:48
 * @detail
 */
@Getter
@Setter
@MessageId(moduleId = MessageIds.HelloModule, commandId = MessageIds.Hello.hello)
public class HelloRequest extends Request {
    @BodyField
    private HelloMessage req;
}
