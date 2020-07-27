package com.github.alonwang.transport.hello;

import com.github.alonwang.transport.core.Session;
import com.github.alonwang.transport.protocol.MessageHandler;
import com.github.alonwang.transport.hello.message.HelloRequest;

/**
 * @author alonwang
 * @date 2020/7/16 17:38
 * @detail
 */
@MessageHandler
public interface HelloService {
    void hello(Session session,HelloRequest request);
}
