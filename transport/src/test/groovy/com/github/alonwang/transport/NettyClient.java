package com.github.alonwang.transport;

import com.github.alonwang.transport.handler.ProtobufRequestDecoder;
import com.github.alonwang.transport.handler.ProtobufResponseDecoder;
import com.github.alonwang.transport.handler.ResponseDispatchHandler;
import com.github.alonwang.transport.protobuf.Base;
import com.github.alonwang.transport.protocol.AbstractRequest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

import java.net.UnknownHostException;

/**
 * @author alonwang
 * @date 2020/7/27 18:03
 * @detail
 */
public class NettyClient {
    private static final ProtobufEncoder protobufEncoder = new ProtobufEncoder();
    private static final ChannelInboundHandler protobufRequestDecoder = new ProtobufRequestDecoder();
    private static final ProtobufDecoder protobufDecoder = new ProtobufDecoder(Base.Request.getDefaultInstance());
    private static final ChannelInboundHandler protobufResponseDecoder=new ProtobufResponseDecoder();
    private static final ChannelInboundHandler responseDispatchHandler=new ResponseDispatchHandler();
    private Channel channel;

    public static void main(String[] args) throws InterruptedException, UnknownHostException {


    }

    public void start() throws UnknownHostException, InterruptedException {
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
                //protobuf encode 固定格式
                pipeline.addLast(new LengthFieldPrepender(4));
                pipeline.addLast(protobufEncoder);
            }
        });
        ChannelFuture future = bootstrap.connect("localhost",80).sync();
        channel = future.channel();
    }

    public void sendMessage(AbstractRequest request) {
        request.encode();
        Base.Request protoRequest = Base.Request.newBuilder().setModuleId(request.header().getModuleId()).setCommandId(request.header().getCommandId()).setData(request.body()).build();
        channel.writeAndFlush(protoRequest);
    }
}
