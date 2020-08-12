package com.github.alonwang.transport.server.handler;

import com.github.alonwang.transport.protobuf.Base;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 *
 * @author alonwang
 * @date 2020/7/13 16:16
 * @detail
 */
public class NioServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    private static final ChannelHandler idleEventHandler = new IdleStateEventHandler();
    private static final ProtobufDecoder protobufDecoder = new ProtobufDecoder(Base.Request.getDefaultInstance());
    private static final ChannelInboundHandler requestDispatchHandler = new RequestDispatchHandler();
    private static final ProtobufEncoder protobufEncoder = new ProtobufEncoder();
    private static final ChannelInboundHandler protobufRequestDecoder = new ProtobufRequestDecoder();
    private static final ChannelOutboundHandler responseEncoder = new ProtobufResponseEncoder();

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new IdleStateHandler(60, 60, 0));
        pipeline.addLast(idleEventHandler);
        //protobuf decode固定格式
        pipeline.addLast(new LengthFieldBasedFrameDecoder(1048576, 0, 4, 0, 4));
        pipeline.addLast(protobufDecoder);
        pipeline.addLast(protobufRequestDecoder);
        pipeline.addLast(requestDispatchHandler);
        //protobuuf encode固定格式
        pipeline.addLast(new LengthFieldPrepender(4));
        pipeline.addLast(protobufEncoder);
        pipeline.addLast(responseEncoder);
    }
}
