package com.github.alonwang.transport.handler;

import com.github.alonwang.transport.protocol.AbstractRequest;
import com.github.alonwang.transport.protocol.factory.MessageFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

import static com.github.alonwang.transport.protobuf.Base.Request;

/**
 * 将protobuf转换为封装的request
 *
 * @author alonwang
 * @date 2020/7/13 17:29
 * @detail
 */
@ChannelHandler.Sharable
public class ProtobufRequestDecoder extends MessageToMessageDecoder<Request> {


    @Override
    protected void decode(ChannelHandlerContext ctx, Request msg, List<Object> out) throws Exception {
        int moduleId = msg.getModuleId();
        int commandId = msg.getCommandId();

        AbstractRequest request = MessageFactory.createRequest(moduleId, commandId, msg.getData());

        out.add(request);
    }
}
