package com.github.alonwang.transport.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
