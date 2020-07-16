package com.github.alonwang.transport.hello;

import com.github.alonwang.transport.core.protocol.MessageHandler;
import com.github.alonwang.transport.core.protocol.ModuleHandler;
import com.github.alonwang.transport.protobuf.Hello.HelloMessage;

/**
 * @author alonwang
 * @date 2020/7/16 17:38
 * @detail
 */
@ModuleHandler(1)
public interface HelloService {
    @MessageHandler(1)
    void hello(HelloMessage request);
}
