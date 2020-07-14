package com.github.alonwang.transport.business

import com.github.alonwang.transport.handler.NioServerChannelInitializer
import com.github.alonwang.transport.server.NettyServer
import spock.lang.Specification

/**
 * @author alonwang* @date 2020/7/15 12:00 上午
 * @detail
 */
class HelloServiceTest extends Specification {

    def setupSpec() {
        new NettyServer().start(8080, new NioServerChannelInitializer());

    }

}
