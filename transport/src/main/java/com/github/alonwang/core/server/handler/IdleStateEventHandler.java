package com.github.alonwang.core.server.handler;

import com.github.alonwang.core.Context;
import com.github.alonwang.core.server.task.Session;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 处理空闲Channel
 *
 * @author alonwang
 * @date 2020/7/13 15:27
 * @detail
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class IdleStateEventHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (!(evt instanceof IdleStateEvent)) {
            return;
        }

        IdleStateEvent event = (IdleStateEvent) evt;
        if (event == IdleStateEvent.READER_IDLE_STATE_EVENT || event == IdleStateEvent.WRITER_IDLE_STATE_EVENT) {
            ctx.channel().close();

        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //channel关闭后,移除相关的session
        Session removedSession = Context.getSessionManager().removeSession(ctx.channel());
        if (removedSession != null) {
            log.debug("channel close,remove related Session({})", removedSession);
        }
    }
}
