package com.github.alonwang.transport.handler;

import com.github.alonwang.transport.protocol.AbstractResponse;
import com.github.alonwang.transport.protocol.factory.MessageFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

import static com.github.alonwang.transport.protobuf.Base.Response;

/**
 * @author alonwang
 * @date 2020/7/29 11:14 下午
 * @detail
 */
public class ProtobufResponseDecoder extends MessageToMessageEncoder<Response> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Response msg, List<Object> out) throws Exception {
        AbstractResponse abstractResponse = MessageFactory.createResponse(msg.getModuleId(), msg.getCommandId());
        abstractResponse.decode();
        //TODO 先简单输出
        System.out.println();
    }
}
