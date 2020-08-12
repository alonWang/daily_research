package com.github.alonwang.transport;

import com.github.alonwang.transport.client.NettyClient;
import com.github.alonwang.transport.hello.message.HelloRequest;
import com.github.alonwang.transport.protobuf.Hello;
import com.github.alonwang.transport.protocol.factory.MessageFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author alonwang
 * @date 2020/8/3 20:03
 * @detail
 */
@SpringBootApplication
public class ClientStarter implements CommandLineRunner {
    public static void main(String[] args) throws Exception {
        new SpringApplication().run(ClientStarter.class, args);

    }


    @Override
    public void run(String... args) throws Exception {
        NettyClient nettyClient = new NettyClient();
        nettyClient.start(80);
        Hello.HelloMessage msg = Hello.HelloMessage.newBuilder().setMsg("123").build();
        HelloRequest helloRequest = MessageFactory.createRequest(1, 1, msg.toByteString());
        nettyClient.sendMessage(helloRequest);
    }
}
