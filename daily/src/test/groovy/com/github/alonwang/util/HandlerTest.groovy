package com.github.alonwang.util

import com.github.alonwang.util.handler.WearableDeviceHandlerRegister
import com.github.alonwang.util.handler.WearableDeviceType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import spock.lang.Specification

/**
 * @author alonwang* @date 2020/8/22 3:46 下午
 * @detail
 */
@SpringBootTest
class HandlerTest extends Specification {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private WearableDeviceHandlerRegister register;

    def "test handler"() {
        expect:
        register.getHandlers().size() == 2;
        register.getHandler(WearableDeviceType.Watch) != null;
        register.getHandler(WearableDeviceType.Bracelet) != null;
    }
}
