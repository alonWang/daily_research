package com.github.alonwang.core;

import com.github.alonwang.core.protocol.factory.MessageFactory;
import com.github.alonwang.core.server.NettyServer;
import com.github.alonwang.core.server.handler.NioServerChannelInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author alonwang
 * @date 2020/10/21 6:06 下午
 * @detail
 */
@Slf4j
@Component
public class SpringStartListener implements ApplicationListener<ApplicationStartedEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        MessageFactory messageFactory = event.getApplicationContext().getBean(MessageFactory.class);
        try {
            new NettyServer().start(80, new NioServerChannelInitializer(messageFactory));
        } catch (Exception e) {
            log.error("Netty start failed", e);
        }
    }
}
