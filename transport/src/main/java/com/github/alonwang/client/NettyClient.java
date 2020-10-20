package com.github.alonwang.client;

import com.github.alonwang.client.handler.ProtobufResponseDecoder;
import com.github.alonwang.client.handler.ResponseDispatchHandler;
import com.github.alonwang.core.core.MessageRegistry;
import com.github.alonwang.core.protocol.AbstractRequest;
import com.github.alonwang.core.protocol.factory.MessageFactory;
import com.github.alonwang.core.protocol.protobuf.Base;
import com.github.alonwang.core.server.handler.ProtobufRequestDecoder;
import com.github.alonwang.logic.hello.message.HelloRequest;
import com.github.alonwang.logic.protobuf.Hello;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

/**
 * @author alonwang
 * @date 2020/7/27 18:03
 * @detail
 */
public class NettyClient {
    private static final MessageFactory messageFactory;

    static {
        MessageRegistry messageRegistry = new MessageRegistry();
        try {
            messageRegistry.init();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        messageFactory = new MessageFactory(messageRegistry);
    }

    private static final ProtobufEncoder protobufEncoder = new ProtobufEncoder();
    private static final ChannelInboundHandler protobufRequestDecoder = new ProtobufRequestDecoder();
    private static final ProtobufDecoder protobufDecoder = new ProtobufDecoder(Base.Response.getDefaultInstance());
    private static final ChannelInboundHandler protobufResponseDecoder = new ProtobufResponseDecoder(messageFactory);
    private static final ChannelInboundHandler responseDispatchHandler = new ResponseDispatchHandler();
    private Channel channel;


    public void start(int serverPort) throws UnknownHostException, InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(bossGroup).channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                //protobuf decode固定格式
                pipeline.addLast(new LengthFieldBasedFrameDecoder(1048576, 0, 4, 0, 4));
                pipeline.addLast(protobufDecoder);
                pipeline.addLast(protobufResponseDecoder);
                pipeline.addLast(responseDispatchHandler);
                //protobuf encode固定格式
                pipeline.addLast(new LengthFieldPrepender(4));
                pipeline.addLast(protobufEncoder);
            }
        });
        ChannelFuture future = bootstrap.connect("localhost", serverPort).sync();
        channel = future.channel();
    }

    public void sendMessage(AbstractRequest request) {
        Base.Request protoRequest =
                Base.Request.newBuilder().setModuleId(request.header().getModuleId()).setCommandId(request.header().getCommandId()).setData(request.body()).build();
        channel.writeAndFlush(protoRequest);
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        NettyClient client = new NettyClient();
        client.start(80);

        while (true) {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String input = bufferedReader.readLine();
            if (input.equals("stop")) {
                break;
            }
            //TODO moduleId规范化
            Hello.HelloMessage helloMessage = Hello.HelloMessage.newBuilder().setMsg(input).build();
            HelloRequest request = messageFactory.createRequest(1, 1, helloMessage.toByteString());
            client.sendMessage(request);
        }
    }
}
