package com.github.alonwang.clu.command.impl;

import com.github.alonwang.clu.command.Command;
import com.github.alonwang.clu.command.CommandResp;
import com.github.alonwang.clu.emum.SID;
import com.github.alonwang.clu.group.GroupManager;
import com.github.alonwang.clu.resp.UserResp;

import io.netty.channel.ChannelHandlerContext;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-12-05 16:58
 **/
public class ConnectCommand implements Command {
    @Override
    public void execute(ChannelHandlerContext ctx) {

        GroupManager.channelGroup().add(ctx.channel());
        UserResp body = new UserResp(ctx.channel().hashCode());
        GroupManager.channelGroup().writeAndFlush(CommandResp.newInstance(SID.USER_CONNECT.value(), body));
        // 推送当前所有人,词语
    }
}
