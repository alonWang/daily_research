package com.github.alonwang.transport.server.handler;

import com.github.alonwang.transport.protobuf.Base;
import com.github.alonwang.transport.protocol.AbstractResponse;
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
public class ProtobufResponseEncoder extends MessageToMessageEncoder<AbstractResponse> {
    @Override
    protected void encode(ChannelHandlerContext ctx, AbstractResponse msg, List<Object> out) throws Exception {
        Base.Response.Builder response = Base.Response.newBuilder();
        response.setModuleId(msg.header().getModuleId()).setCommandId(msg.header().getCommandId()).setErrorCode(msg.header().getErrorCode());
        response.setData(msg.body());
        out.add(response);
    }
}
