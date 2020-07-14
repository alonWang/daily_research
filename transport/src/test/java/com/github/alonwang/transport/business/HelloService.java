package com.github.alonwang.transport.business;

import com.github.alonwang.transport.protobuf.Hello.HelloMessage;
import com.github.alonwang.transport.protobuf.Hello.MeToMessage;

/**
 * @author alonwang
 * @date 2020/7/14 11:22 下午
 * @detail
 */
public interface HelloService {

   MeToMessage saySomething(HelloMessage request);
}
