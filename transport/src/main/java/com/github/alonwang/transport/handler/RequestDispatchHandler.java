package com.github.alonwang.transport.handler;


import com.github.alonwang.transport.core.Context;
import com.github.alonwang.transport.core.Session;
import com.github.alonwang.transport.protocol.AbstractRequest;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Request分发器,将Request分发到业务线程池中
 *
 * @author alonwang
 * @date 2020/7/13 16:46
 * @detail
 */
@ChannelHandler.Sharable
public class RequestDispatchHandler extends SimpleChannelInboundHandler<AbstractRequest> {
    private static Map<Channel, Session> channel2SessionMap = new ConcurrentHashMap<>();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AbstractRequest msg) throws Exception {
        Session session = channel2SessionMap.compute(ctx.channel(), (c, s) -> {
            if (null == s) {
                Session createSession = new Session();
                createSession.setChannel(c);
                return createSession;
            } else {
                return s;
            }

        });

        Context.getRequestDispatchService().dispatch(session, msg);
    }
}
