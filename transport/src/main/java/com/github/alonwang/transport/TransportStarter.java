package com.github.alonwang.transport;

import com.github.alonwang.transport.handler.NioServerChannelInitializer;
import com.github.alonwang.transport.server.NettyServer;
import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;

/**
 * @author alonwang
 * @date 2020/7/23 17:52
 * @detail
 */
@SpringBootApplication
public class TransportStarter implements CommandLineRunner {
    public static void main(String[] args) throws Exception {
        new SpringApplication().run(TransportStarter.class, args);

    }


    @Override
    public void run(String... args) throws Exception {
        new NettyServer().start(80, new NioServerChannelInitializer());
    }
}
