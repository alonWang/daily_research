package com.github.alonwang.core.server.handler;


import com.github.alonwang.core.Context;
import com.github.alonwang.core.protocol.Request;
import com.github.alonwang.core.server.task.JobExceptionLogProxy;
import com.github.alonwang.core.server.task.MethodWrapper;
import com.github.alonwang.core.server.task.RequestJob;
import com.github.alonwang.core.server.task.Session;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 找到channel对应的User，并将消息分发到业务线程池
 *
 * @author alonwang
 * @date 2020/7/13 16:46
 * @detail
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class RequestDispatchHandler extends SimpleChannelInboundHandler<Request> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Request msg) throws Exception {
        final int moduleId = msg.header().getModuleId();
        final int commandId = msg.header().getCommandId();
        MethodWrapper wrapper = Context.getMethodRegistry().getWrapper(moduleId,
                commandId);
        if (wrapper == null) {
            log.warn("no method wrapper for moduleId({}),commandId({})",
                    moduleId, commandId);
            return;
        }

        Optional<Session> optSession = Context.getSessionManager().getOrCreateSession(ctx.channel());
        if (optSession.isEmpty()) {
            return;
        }

        Session session = optSession.get();
        session.execute(new JobExceptionLogProxy<>(new RequestJob(wrapper, msg)));
    }
}
