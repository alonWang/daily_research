package com.github.alonwang.core;

import com.github.alonwang.core.core.MessageRegistry;
import com.github.alonwang.core.protocol.Message;
import com.github.alonwang.core.protocol.ProtobufCodecDelegate;
import com.github.alonwang.core.protocol.factory.MessageFactory;
import com.github.alonwang.core.server.NettyServer;
import com.github.alonwang.core.server.handler.NioServerChannelInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 系统启动入口
 *
 * @author alonwang
 * @date 2020/10/21 6:06 下午
 * @detail
 */
@Slf4j
@Component
public class SpringStartedListener implements ApplicationListener<ApplicationStartedEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        registerCodec(applicationContext);
        startNetty(applicationContext);


    }

    private void registerCodec(ApplicationContext context) {
        MessageRegistry messageRegistry = context.getBean(MessageRegistry.class);
        for (Class<? extends Message<?>> clazz : messageRegistry.getMessages().values()) {
            ProtobufCodecDelegate.register(clazz);
        }
    }

    private void startNetty(ApplicationContext applicationContext) {
        try {
            new NettyServer().start(80, applicationContext.getBean(NioServerChannelInitializer.class));
        } catch (Exception e) {
            log.error("Netty start failed", e);
        }
    }
}
