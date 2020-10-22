package com.github.alonwang.logic.hello.message;

import com.github.alonwang.core.protocol.MessageId;
import com.github.alonwang.core.protocol.Response;
import com.github.alonwang.logic.core.MessageIds;
import lombok.Getter;
import lombok.Setter;

import static com.github.alonwang.logic.protobuf.Hello.MeToMessage;

/**
 * @author alonwang
 * @date 2020/7/22 18:05
 * @detail
 */
@Getter
@Setter
@MessageId(moduleId = MessageIds.HelloModule, commandId = MessageIds.Hello.meTo)
public class HelloResponse extends Response {
    private MeToMessage message;
}
