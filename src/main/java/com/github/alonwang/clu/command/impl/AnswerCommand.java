package com.github.alonwang.clu.command.impl;

import com.github.alonwang.clu.command.Command;
import com.github.alonwang.clu.command.CommandResp;
import com.github.alonwang.clu.emum.SID;
import com.github.alonwang.clu.exception.BusinessException;
import com.github.alonwang.clu.group.GroupManager;
import com.github.alonwang.clu.idiom.IdiomManager;
import com.github.alonwang.clu.resp.AnswerCorrectResp;

import io.netty.channel.ChannelHandlerContext;

import cn.hutool.core.util.StrUtil;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-12-05 15:41
 **/
public class AnswerCommand implements Command {
    private String answer;

    public AnswerCommand(String answer) {
        this.answer = answer;
    }

    @Override
    public void execute(ChannelHandlerContext ctx) {
        if (StrUtil.isEmpty(answer) || answer.length() != 4) {
            throw new BusinessException("只能是四字成语哦~");
        }
        boolean correct = IdiomManager.correct(answer);
        int channelId = ctx.channel().hashCode();
        AnswerCorrectResp resp = new AnswerCorrectResp(correct, channelId, answer);
        GroupManager.channelGroup().writeAndFlush(CommandResp.newInstance(SID.USER_ANSWER.value(), resp));
        if (correct) {
            GroupManager.channelGroup().writeAndFlush(CommandResp.newInstance(SID.NEW_WORD.value(), IdiomManager.current()));
        }
    }
}
