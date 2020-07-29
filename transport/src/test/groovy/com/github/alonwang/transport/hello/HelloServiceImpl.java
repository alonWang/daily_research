package com.github.alonwang.transport.hello;

import com.github.alonwang.transport.core.Context;
import com.github.alonwang.transport.core.User;
import com.github.alonwang.transport.hello.message.HelloRequest;
import com.github.alonwang.transport.hello.message.HelloResponse;
import com.github.alonwang.transport.protocol.factory.MessageFactory;
import org.springframework.stereotype.Service;

/**
 * @author alonwang
 * @date 2020/7/24 11:47
 * @detail
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public void hello(User user, HelloRequest request) {
        System.out.println(request.getBody().getMsg());
        HelloResponse response=MessageFactory.createResponse(1,2);


    }
}
