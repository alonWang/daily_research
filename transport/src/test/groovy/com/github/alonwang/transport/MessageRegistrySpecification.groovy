package com.github.alonwang.transport

import com.github.alonwang.transport.core.Context
import com.github.alonwang.transport.core.MessageRegistry
import com.github.alonwang.transport.protobuf.Hello
import com.github.alonwang.transport.protocol.AbstractRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

/**
 * @author alonwang* @date 2020/7/24 11:20
 * @detail
 */
@SpringBootTest(classes = TransportStarter.class)
class MessageRegistrySpecification extends Specification {
    @Autowired
    MessageRegistry messageRegistry;

    def "test messages"() {
        expect:
        def clazz = messageRegistry.getMessage(1, 1)
        assert clazz != null
        def wrapper = messageRegistry.getWrapper(clazz)
        assert wrapper != null
    }

    def "test send message"() {
        expect:
        NettyClient nettyClient = new NettyClient();
        nettyClient.start();
        Hello.HelloMessage msg = Hello.HelloMessage.newBuilder().setMsg("123").build();
        AbstractRequest abstractRequest = Context.getMessageFactory().createRequest(1, 1, msg.toByteString());
        nettyClient.sendMessage(abstractRequest);

    }
}
