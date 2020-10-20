package com.github.alonwang.client.handler;

import com.github.alonwang.core.protocol.AbstractResponse;
import com.github.alonwang.core.protocol.factory.MessageFactory;
import com.github.alonwang.core.protocol.protobuf.Base.Response;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;


/**
 * @author alonwang
 * @date 2020/7/29 11:14 下午
 * @detail
 */
@ChannelHandler.Sharable
public class ProtobufResponseDecoder extends MessageToMessageDecoder<Response> {
    private MessageFactory messageFactory;

    public ProtobufResponseDecoder(MessageFactory messageFactory) {
        this.messageFactory = messageFactory;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, Response msg, List<Object> out) throws Exception {
        AbstractResponse abstractResponse = messageFactory.createResponse(msg.getModuleId(),
                msg.getCommandId());
        abstractResponse.setBody(msg.getData());
        abstractResponse.decode();
        out.add(abstractResponse);
    }
}
