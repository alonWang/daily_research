package com.github.alonwang.transport;

import com.github.alonwang.transport.client.NettyClient;
import com.github.alonwang.transport.handler.NioServerChannelInitializer;
import com.github.alonwang.transport.server.NettyServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author alonwang
 * @date 2020/8/3 20:03
 * @detail
 */
@SpringBootApplication()
public class ClientStarter implements CommandLineRunner {
    public static void main(String[] args) throws Exception {
        new SpringApplication().run(ClientStarter.class, args);

    }


    @Override
    public void run(String... args) throws Exception {
        NettyClient nettyClient=new NettyClient();
        nettyClient.start(80);
    }
}
