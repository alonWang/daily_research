package com.github.alonwang.clu.command.impl;

import com.github.alonwang.clu.command.Command;
import com.github.alonwang.clu.command.CommandResp;
import com.github.alonwang.clu.emum.SID;
import com.github.alonwang.clu.manager.GroupManager;

import io.netty.channel.ChannelHandlerContext;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-12-05 17:04
 **/
public class DisconnectCommand implements Command {
    @Override
    public void execute(ChannelHandlerContext ctx) {
        GroupManager.channelGroup().remove(ctx.channel());
        GroupManager.channelGroup().writeAndFlush(CommandResp.newInstance(
                SID.USER_DISCONNECT.value(), ctx.channel().hashCode()));
    }
}
