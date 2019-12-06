package com.github.alonwang.clu.handler;

import com.github.alonwang.clu.command.Command;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-12-05 17:15
 **/
@ChannelHandler.Sharable
public class BusinessHandler extends SimpleChannelInboundHandler<Command> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Command msg) throws Exception {
        msg.execute(ctx);
    }

}
