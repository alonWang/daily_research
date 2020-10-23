package com.github.alonwang.logic.hello;

import com.github.alonwang.core.protocol.MessageController;
import com.github.alonwang.core.server.task.Session;
import com.github.alonwang.logic.hello.message.HelloRequest;

/**
 * @author alonwang
 * @date 2020/7/16 17:38
 * @detail
 */
@MessageController
public interface HelloService {
    void hello(Session session, HelloRequest request);

}
