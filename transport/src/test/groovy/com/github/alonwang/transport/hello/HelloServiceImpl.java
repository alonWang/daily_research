package com.github.alonwang.transport.hello;

import com.github.alonwang.transport.hello.message.HelloRequest;
import com.github.alonwang.transport.protobuf.Hello;
import org.springframework.stereotype.Service;

/**
 * @author alonwang
 * @date 2020/7/24 11:47
 * @detail
 */
@Service
public class HelloServiceImpl implements HelloService{
    @Override
    public void hello(HelloRequest request) {

    }
}
