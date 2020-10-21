package com.github.alonwang.core.server.handler;

import com.github.alonwang.core.Context;
import com.github.alonwang.core.protocol.AbstractRequest;
import com.github.alonwang.core.protocol.protobuf.Base.Request;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;


/**
 * 将protobuf的request转换为封装的request
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
        AbstractRequest request = Context.getMessageFactory().parseRequest(moduleId, commandId, msg.getData());
        out.add(request);
    }
}
