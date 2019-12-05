package com.github.alonwang.clu.command.impl;

import com.github.alonwang.clu.command.Command;
import com.github.alonwang.clu.command.CommandResp;
import com.github.alonwang.clu.emum.SID;
import com.github.alonwang.clu.group.GroupManager;
import com.github.alonwang.clu.idiom.IdiomManager;
import com.github.alonwang.clu.resp.HomepageResp;

import io.netty.channel.ChannelHandlerContext;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @description:
 * @author: alonwang
 * @create: 2019-12-05 14:27
 **/
public class HomepageCommand implements Command {

    @Override
    public void execute(ChannelHandlerContext ctx) {
        List<Integer> channelIds = GroupManager.channelGroup().stream().map(Object::hashCode).collect(Collectors.toList());

        HomepageResp resp = new HomepageResp(channelIds, ctx.channel().hashCode(), IdiomManager.current());
        ctx.writeAndFlush(CommandResp.newInstance(SID.HOMEPAGE.value(), resp));
    }
}
