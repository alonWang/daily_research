package com.github.alonwang.transport.handler;

import com.github.alonwang.transport.protobuf.Base;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author alonwang
 * @date 2020/7/13 16:16
 * @detail
 */
public class NioServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    private static final ChannelHandler idleEventHandler = new IdleStateEventHandler();
    private static final ProtobufDecoder protobufDecoder = new ProtobufDecoder(Base.Request.getDefaultInstance());
    private static final ChannelInboundHandler requestDispatcher = new RequestDispatcher();
    private static final ProtobufEncoder protobufEncoder = new ProtobufEncoder();
    private static final ChannelInboundHandler requestWrapper = new ProtobufRequestDecoder();

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new IdleStateHandler(60, 60, 0));
        pipeline.addLast(idleEventHandler);
        //protobuf decode固定格式
        pipeline.addLast(new LengthFieldBasedFrameDecoder(1048576, 0, 4, 0, 4));
        pipeline.addLast(protobufDecoder);
        pipeline.addLast(requestWrapper);
        pipeline.addLast(requestDispatcher);
        //protobuuf encode固定格式
        pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
        pipeline.addLast(protobufEncoder);
    }
}
