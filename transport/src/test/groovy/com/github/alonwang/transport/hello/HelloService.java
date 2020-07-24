package com.github.alonwang.transport.hello;

import com.github.alonwang.transport.core.protocol.MessageHandler;
import com.github.alonwang.transport.hello.message.HelloRequest;
import com.github.alonwang.transport.protobuf.Hello.HelloMessage;

/**
 * @author alonwang
 * @date 2020/7/16 17:38
 * @detail
 */
@MessageHandler
public interface HelloService {
    void hello(HelloRequest request);
}
