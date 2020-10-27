package com.github.alonwang.logic.hello.controller;

import com.github.alonwang.core.protocol.annotation.MessageController;
import com.github.alonwang.core.netty.Session;
import com.github.alonwang.logic.hello.message.HelloRequest;
import com.github.alonwang.logic.hello.message.HelloResponse;

/**
 * @author alonwang
 * @date 2020/7/16 17:38
 * @detail
 */
@MessageController
public interface HelloController {
    HelloResponse hello(Session session, HelloRequest request);

}
