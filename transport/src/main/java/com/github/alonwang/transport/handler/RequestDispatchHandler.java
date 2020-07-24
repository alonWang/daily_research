package com.github.alonwang.transport.handler;


import com.github.alonwang.transport.core.Context;
import com.github.alonwang.transport.core.Session;
import com.github.alonwang.transport.protocol.AbstractRequest;
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
public class RequestDispatchHandler extends SimpleChannelInboundHandler<AbstractRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AbstractRequest msg) throws Exception {
        Session session = new Session();
        Context.getRequestDispatchService().dispatch(session, msg);
    }
}
