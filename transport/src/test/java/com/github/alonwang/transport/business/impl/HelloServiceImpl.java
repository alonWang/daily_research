package com.github.alonwang.transport.business.impl;

import com.github.alonwang.transport.business.HelloService;
import com.github.alonwang.transport.protobuf.Hello.HelloMessage;
import com.github.alonwang.transport.protobuf.Hello.MeToMessage;

/**
 * @author alonwang
 * @date 2020/7/14 11:22 下午
 * @detail
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public MeToMessage saySomething(HelloMessage request) {
        System.out.println(request.getMsg());
        return MeToMessage.newBuilder().setMsg(request.getMsg()).build();
    }
}
