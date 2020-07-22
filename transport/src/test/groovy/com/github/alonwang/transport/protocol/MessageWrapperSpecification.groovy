package com.github.alonwang.transport.protocol


import com.github.alonwang.transport.protobuf.Base
import com.github.alonwang.transport.protobuf.Hello
import spock.lang.Specification

/**
 * @author alonwang* @date 2020/7/15 7:34 上午
 * @detail
 */
class MessageWrapperSpecification extends Specification {
    def "proto to request"() {
        given:
        Hello.HelloMessage body = Hello.HelloMessage.newBuilder().setMsg("hello").build();
        Base.Request request = Base.Request.newBuilder().setHeader(header).setBody(body.toByteString()).build();

    }

    def "response to proto"() {

    }

    def "protoRequest"(){

    }

}
