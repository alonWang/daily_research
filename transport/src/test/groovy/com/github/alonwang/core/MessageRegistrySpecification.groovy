package com.github.alonwang.core

import com.github.alonwang.TransportStarter
import com.github.alonwang.core.core.MessageRegistry
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
        def wrapper = Context.getMethodRegistry().getWrapper(clazz)
        assert wrapper != null
    }


}
