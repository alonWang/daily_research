package com.github.alonwang.core.netty.handler;

import com.github.alonwang.core.Context;
import com.github.alonwang.core.protocol.message.Request;
import com.github.alonwang.core.protocol.protobuf.Base.Protocol;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 将{@link Protocol}转换为封装的request
 *
 * @author alonwang
 * @date 2020/7/13 17:29
 * @detail
 */
@Component
@ChannelHandler.Sharable
public class ProtobufRequestDecoder extends MessageToMessageDecoder<Protocol> {


    @Override
    protected void decode(ChannelHandlerContext ctx, Protocol protocol,
                          List<Object> out) throws Exception {
        Request request = Context.getMessageFactory().toMessage(protocol);
        out.add(request);
    }
}
