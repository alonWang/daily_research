package com.github.alonwang.clu.command.impl;

import com.github.alonwang.clu.command.Command;
import com.github.alonwang.clu.command.CommandResp;
import com.github.alonwang.clu.emum.SID;
import com.github.alonwang.clu.group.GroupManager;
import com.github.alonwang.clu.resp.UserInputChangeResp;

import io.netty.channel.ChannelHandlerContext;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-12-06 08:59
 **/
public class InputChangeCommand implements Command {
    private String word;

    public InputChangeCommand(String word) {
        this.word = word;
    }

    @Override
    public void execute(ChannelHandlerContext ctx) {
        UserInputChangeResp resp = new UserInputChangeResp(ctx.channel().hashCode(), word);
        GroupManager.channelGroup().writeAndFlush(CommandResp.newInstance(SID.USER_INPUT_CHANGE.value(), resp));
    }
}
