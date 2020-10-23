package com.github.alonwang.core.server.handler;


import com.github.alonwang.core.Context;
import com.github.alonwang.core.protocol.Request;
import com.github.alonwang.core.server.task.MethodWrapper;
import com.github.alonwang.core.server.task.RequestJob;
import com.github.alonwang.core.server.task.Session;
import com.google.common.base.Preconditions;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Optional;

/**
 * 找到channel对应的User，并将消息分发到业务线程池
 *
 * @author alonwang
 * @date 2020/7/13 16:46
 * @detail
 */
@ChannelHandler.Sharable
public class RequestDispatchHandler extends SimpleChannelInboundHandler<Request> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Request msg) throws Exception {
        Optional<Session> optSession = Context.getSessionManager().getOrCreateSession(ctx.channel());
        if (optSession.isEmpty()) {
            return;
        }

        Session session = optSession.get();
        MethodWrapper wrapper = Context.getMethodRegistry().getWrapper(msg.header().getModuleId(),
                msg.header().getCommandId());
        Preconditions.checkNotNull(wrapper);
        session.execute(new RequestJob(wrapper, msg));
    }
}
