package com.github.alonwang.transport.client.handler;

import com.github.alonwang.transport.protocol.AbstractResponse;
import com.github.alonwang.transport.protocol.factory.MessageFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

import static com.github.alonwang.transport.protobuf.Base.Response;

/**
 * @author alonwang
 * @date 2020/7/29 11:14 下午
 * @detail
 */
@ChannelHandler.Sharable
public class ProtobufResponseDecoder extends MessageToMessageDecoder<Response> {
    @Override
    protected void decode(ChannelHandlerContext ctx, Response msg, List<Object> out) throws Exception {
        AbstractResponse abstractResponse = MessageFactory.createResponse(msg.getModuleId(), msg.getCommandId());
        abstractResponse.setBody(msg.getData());
        abstractResponse.decode();
        out.add(abstractResponse);
    }
}
