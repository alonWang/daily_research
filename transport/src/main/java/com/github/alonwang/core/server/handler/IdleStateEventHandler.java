package com.github.alonwang.core.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 处理空闲Channel
 *
 * @author alonwang
 * @date 2020/7/13 15:27
 * @detail
 */
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
}