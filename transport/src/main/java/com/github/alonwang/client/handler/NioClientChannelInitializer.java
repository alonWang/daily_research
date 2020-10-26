package com.github.alonwang.client.handler;

import com.github.alonwang.core.protocol.factory.MessageFactory;
import com.github.alonwang.core.protocol.protobuf.Base;
import com.github.alonwang.core.netty.handler.ProtobufRequestDecoder;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

/**
 * @author alonwang
 * @date 2020/10/23 3:16 下午
 */
public class NioClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    private static final ProtobufEncoder protobufEncoder = new ProtobufEncoder();
    private static final ChannelInboundHandler protobufRequestDecoder = new ProtobufRequestDecoder();
    private static final ProtobufDecoder protobufDecoder = new ProtobufDecoder(Base.Protocol.getDefaultInstance());
    private static final ChannelInboundHandler responseDispatchHandler = new ResponseDispatchHandler();
    private  final ChannelInboundHandler protobufResponseDecoder ;

    public NioClientChannelInitializer(MessageFactory messageFactory) {
        this.protobufResponseDecoder = new ProtobufResponseDecoder(messageFactory);
    }


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
}
