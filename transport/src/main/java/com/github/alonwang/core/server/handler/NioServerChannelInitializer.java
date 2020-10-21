package com.github.alonwang.core.server.handler;


import com.github.alonwang.core.protocol.protobuf.Base;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelPipeline;
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
    private static final ProtobufDecoder protobufDecoder = new ProtobufDecoder(Base.Protocol.getDefaultInstance());
    private static final ChannelInboundHandler requestDispatchHandler = new RequestDispatchHandler();
    private static final ProtobufEncoder protobufEncoder = new ProtobufEncoder();
    private static final ChannelInboundHandler protobufRequestDecoder = new ProtobufRequestDecoder();
    private static final ChannelOutboundHandler responseEncoder = new ProtobufResponseEncoder();

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //闲置链接监听
        pipeline.addLast(new IdleStateHandler(60, 60, 0));
        //闲置链接处理单元
        pipeline.addLast(idleEventHandler);
        //1 protobuf解码单元
        pipeline.addLast(new LengthFieldBasedFrameDecoder(1048576, 0, 4, 0, 4));
        pipeline.addLast(protobufDecoder);
        //2 protobuf封装为自定义协议Request单元
        pipeline.addLast(protobufRequestDecoder);
        //3 Request分发执行单元
        pipeline.addLast(requestDispatchHandler);
        //two protobuuf编码单元
        pipeline.addLast(new LengthFieldPrepender(4));
        pipeline.addLast(protobufEncoder);
        //one Response转换为protobuf单元
        pipeline.addLast(responseEncoder);
    }
}
