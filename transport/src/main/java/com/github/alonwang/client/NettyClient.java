package com.github.alonwang.client;

import com.github.alonwang.TransportStarter;
import com.github.alonwang.client.handler.NioClientChannelInitializer;
import com.github.alonwang.core.core.MessageRegistry;
import com.github.alonwang.core.protocol.Message;
import com.github.alonwang.core.protocol.ProtobufCodecDelegate;
import com.github.alonwang.core.protocol.Request;
import com.github.alonwang.core.protocol.factory.MessageFactory;
import com.github.alonwang.core.protocol.protobuf.Base;
import com.github.alonwang.logic.core.MessageIds;
import com.github.alonwang.logic.hello.message.HelloRequest;
import com.github.alonwang.logic.protobuf.Hello;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

/**
 * 简单的客户端实现,具备命令行交互能力.
 * <p>
 * 启动{@link TransportStarter}后,再运行客户端.通过命令行发送文字实现与服务端交互,输入{@link NettyClient#TERMINATE_INPUT}终止运行.
 *
 * @author alonwang
 * @date 2020/7/27 18:03
 * @detail
 */
public class NettyClient {
    private static final MessageFactory messageFactory;
    /**
     * 结束与服务端交互的输入
     */
    private static final String TERMINATE_INPUT = "stop";
    /**
     * 是否连接上服务端
     */
    private volatile boolean connected;
    /**
     * 客户端线程池 独占
     */
    private EventLoopGroup workerGroup;
    /**
     * 绑定的Netty Channel
     */
    private Channel channel;

    static {
        MessageRegistry messageRegistry = new MessageRegistry();
        try {
            messageRegistry.init();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        messageFactory = new MessageFactory(messageRegistry);

        for (Class<? extends Message<?>> messageClazz : messageRegistry.getMessages().values()) {
            ProtobufCodecDelegate.register(messageClazz);
        }
    }


    public NettyClient() {
    }

    public void start(int serverPort) throws UnknownHostException, InterruptedException {
        workerGroup = new NioEventLoopGroup(1);
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new NioClientChannelInitializer(messageFactory));
        ChannelFuture future = bootstrap.connect("localhost", serverPort).sync();
        connected = true;
        channel = future.channel();
        channel.closeFuture().addListener((f) -> {
            System.out.println("服务端断开连接...");
            connected = false;
            workerGroup.shutdownGracefully();
            System.exit(0);
        });
    }

    /**
     * 关闭客户端,清理相关资源
     */
    public void stop() {
        connected = false;
        channel.close();
        workerGroup.shutdownGracefully();
    }

    /**
     * 是否可以和服务端交互
     *
     * @return
     */
    public boolean alive() {
        return connected && !workerGroup.isShutdown();
    }

    public void sendMessage(Request request) {
        request.encode();
        Base.Protocol protoRequest =
                Base.Protocol.newBuilder().setModuleId(request.header().getModuleId()).setCommandId(request.header().getCommandId()).setPayload(request.getPayload()).build();
        channel.writeAndFlush(protoRequest);
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        NettyClient client = new NettyClient();
        client.start(80);

        while (client.alive()) {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String input = bufferedReader.readLine();
            if (input.equals(TERMINATE_INPUT)) {
                client.stop();
                break;
            }

            HelloRequest request = messageFactory.createMessage(MessageIds.HelloModule, MessageIds.Hello.hello);
            Hello.HelloMessage helloMessage = Hello.HelloMessage.newBuilder().setMsg(input).build();
            request.setReq(helloMessage);
            client.sendMessage(request);
        }

    }
}
