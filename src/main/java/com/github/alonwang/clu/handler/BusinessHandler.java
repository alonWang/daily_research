package com.github.alonwang.clu.handler;

import com.github.alonwang.clu.command.Command;
import com.github.alonwang.clu.idiom.IdiomManager;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import cn.hutool.core.util.StrUtil;
import lombok.extern.java.Log;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-11-15 14:31
 **/
@Log
public class BusinessHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private final static ChannelGroup CHANNEL_GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String answer = msg.text();
        if (StrUtil.isEmpty(answer) || answer.length() != 4) {
            ctx.writeAndFlush(new Command(0, "false"));
            CHANNEL_GROUP.writeAndFlush(new Command(2, "323"));
            return;
        }
        if (IdiomManager.correct(answer)) {
            ctx.writeAndFlush(new Command(1, "true"));
        } else {
            ctx.writeAndFlush(new Command(2, "false"));
        }


    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        CHANNEL_GROUP.add(ctx.channel());
        //推送人员增加
        ctx.writeAndFlush(new Command(0, "false"));
        CHANNEL_GROUP.writeAndFlush(new Command(2, "323"));
        // 推送当前所有人,词语


    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 推送用户退出
        CHANNEL_GROUP.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.warning(ctx.hashCode() + ":" + cause);
    }
}
