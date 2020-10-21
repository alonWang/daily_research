package com.github.alonwang.core.server.handler;

import com.github.alonwang.core.protocol.Response;
import com.github.alonwang.core.protocol.factory.MessageFactory;
import com.github.alonwang.core.protocol.protobuf.Base;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * 将自定义的Response转换为Protobuf的response
 *
 * @author alonwang
 * @date 2020/7/29 10:32 下午
 * @detail
 */
@ChannelHandler.Sharable
public class ProtobufResponseEncoder extends MessageToMessageEncoder<Response> {
    private MessageFactory messageFactory;

    public ProtobufResponseEncoder(MessageFactory messageFactory) {
        this.messageFactory = messageFactory;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Response msg, List<Object> out) throws Exception {
        Base.Protocol protocol = messageFactory.toProtocol(msg);
        out.add(protocol);
    }
}
