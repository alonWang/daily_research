package com.github.alonwang.core


import com.github.alonwang.core.protocol.BodyField
import com.github.alonwang.core.protocol.MessageId
import com.github.alonwang.core.protocol.ProtobufCodecDelegate
import com.github.alonwang.core.protocol.ProtobufMessage
import com.github.alonwang.logic.hello.message.HelloRequest
import com.github.alonwang.logic.protobuf.Hello.HelloMessage
import com.github.alonwang.logic.protobuf.Hello.MeToMessage
import spock.lang.Specification

/** {@link ProtobufCodecDelegate}测试
 * @author alonwang* @date 2020/10/22 5:28 下午
 * @detail
 */

class ProtobufCodecTest extends Specification {
    def cleanup() {
        ProtobufCodecDelegate.getMessageToField().clear();
        ProtobufCodecDelegate.getProtoToParseFromMethod().clear();
    }

    @MessageId(moduleId = 99999, commandId = 99999)
    static class InValidMessage extends ProtobufMessage {
        private HelloMessage hello;
        private MeToMessage meto;

    }

    @MessageId(moduleId = 99999, commandId = 99998)
    static class ValidMessage extends ProtobufMessage {
        @BodyField
        private HelloMessage hello;
        private MeToMessage meto;

    }

    def "register message class"() {
        given:
        ProtobufCodecDelegate.register(messageClazz)
        expect:
        ProtobufCodecDelegate.getMessageToField().containsKey(messageClazz) == result
        ProtobufCodecDelegate.getProtoToParseFromMethod().containsKey(protoClazz) == result
        where:
        messageClazz         | protoClazz         | result
        HelloRequest.class   | HelloMessage.class | true
        InValidMessage.class | MeToMessage.class  | false
        ValidMessage.class   | HelloMessage.class | true

    }

    def "register invalid message class"() {

    }

}
