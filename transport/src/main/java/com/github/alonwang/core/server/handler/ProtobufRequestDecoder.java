package com.github.alonwang.core.server.handler;

import com.github.alonwang.core.Context;
import com.github.alonwang.core.protocol.Request;
import com.github.alonwang.core.protocol.protobuf.Base;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 将protobuf的request转换为封装的request
 *
 * @author alonwang
 * @date 2020/7/13 17:29
 * @detail
 */
@Component
@ChannelHandler.Sharable
public class ProtobufRequestDecoder extends MessageToMessageDecoder<com.github.alonwang.core.protocol.protobuf.Base.Protocol> {


    @Override
    protected void decode(ChannelHandlerContext ctx, Base.Protocol msg,
                          List<Object> out) throws Exception {
        Request request = Context.getMessageFactory().toMessage(msg);
        out.add(request);
    }
}
