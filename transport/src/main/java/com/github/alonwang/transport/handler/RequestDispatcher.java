package com.github.alonwang.transport.handler;


import com.github.alonwang.transport.core.protocol.Request;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Request分发器,将Request分发到业务线程池中
 *
 * @author alonwang
 * @date 2020/7/13 16:46
 * @detail
 */
@ChannelHandler.Sharable
public class RequestDispatcher extends SimpleChannelInboundHandler<Request> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Request msg) throws Exception {


    }
}
