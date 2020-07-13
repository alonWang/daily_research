package com.github.alonwang.transport.handler;

import com.github.alonwang.transport.protobuf.Base;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**将protobuf转换为封装的request
 * @author alonwang
 * @date 2020/7/13 17:29
 * @detail
 */
public class ProtobufRequestDecoder extends MessageToMessageDecoder<Base.Request> {


    @Override
    protected void decode(ChannelHandlerContext ctx, Base.Request msg, List<Object> out) throws Exception {

    }
}
