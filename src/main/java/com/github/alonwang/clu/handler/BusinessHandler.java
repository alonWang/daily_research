package com.github.alonwang.clu.handler;

import com.alibaba.fastjson.JSONObject;
import com.github.alonwang.clu.command.CID;
import com.github.alonwang.clu.command.Command;
import com.github.alonwang.clu.command.SID;
import com.github.alonwang.clu.idiom.IdiomManager;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.netty.util.internal.StringUtil;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.java.Log;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-11-15 14:31
 **/
@Log
public class BusinessHandler extends SimpleChannelInboundHandler<Command> {
    private final static ChannelGroup CHANNEL_GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Command cmd) throws Exception {
        int id = cmd.getId();
        CID cid = CID.valueOf(id);
        if (cid == null) {
            ctx.writeAndFlush(new Command(SID.ERROR.value(), "illegal cid"));
        }
        switch (cid) {
            case CONNECT:
                handleConnect(ctx);
                break;
            case ANSWER:
                handleAnswer(ctx, cmd.getBody());
                break;
            default:
                ctx.writeAndFlush(new Command(SID.ERROR.value(), "illegal cid"));
        }


    }

    private void handleAnswer(ChannelHandlerContext ctx, String body) {
        String answer = body;
        if (StringUtil.isNullOrEmpty(answer) || answer.length() != 4) {
            ctx.writeAndFlush(new Command(SID.ERROR.value(), "word length illegal"));

        }
        boolean correct = IdiomManager.correct(answer);

        int channelId = ctx.channel().hashCode();
        JSONObject result = new JSONObject();
        result.put("correct", correct);
        result.put("id", channelId);
        result.put("word", answer);
        CHANNEL_GROUP.writeAndFlush(new Command(SID.USER_ANSWER.value(), result.toJSONString()));
        if (correct) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("word", IdiomManager.current());
            CHANNEL_GROUP.writeAndFlush(new Command(SID.NEW_WORD.value(), jsonObject.toJSONString()));
        }

    }

    private void handleConnect(ChannelHandlerContext ctx) {
        JSONObject jsonObject = new JSONObject();
        List<Integer> channelIds = CHANNEL_GROUP.stream().map(Object::hashCode).collect(Collectors.toList());
        jsonObject.put("ids", channelIds);
        jsonObject.put("id", ctx.channel().hashCode());
        jsonObject.put("word", IdiomManager.current());
        ctx.writeAndFlush(new Command(SID.HOMEPAGE_INFO.value(), jsonObject.toJSONString()));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        CHANNEL_GROUP.add(ctx.channel());
        //推送人员增加
        JSONObject result = new JSONObject();
        result.put("id", ctx.channel().hashCode());
        CHANNEL_GROUP.writeAndFlush(new Command(SID.NEW_USER_CONNECT.value(), result.toJSONString()));
        // 推送当前所有人,词语


    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 推送用户退出
        CHANNEL_GROUP.remove(ctx.channel());
        JSONObject result = new JSONObject();
        result.put("id", ctx.channel().hashCode());
        CHANNEL_GROUP.writeAndFlush(new Command(SID.USER_DISCONNECT.value(), result.toJSONString()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.warning(ctx.hashCode() + ":" + cause);
    }
}
