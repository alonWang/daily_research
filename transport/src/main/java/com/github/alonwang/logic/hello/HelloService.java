package com.github.alonwang.logic.hello;

import com.github.alonwang.core.protocol.MessageHandler;
import com.github.alonwang.core.server.task.User;
import com.github.alonwang.logic.hello.message.HelloRequest;

/**
 * @author alonwang
 * @date 2020/7/16 17:38
 * @detail
 */
@MessageHandler
public interface HelloService {
    void hello(User user, HelloRequest request);

}
