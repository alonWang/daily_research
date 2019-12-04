package com.github.alonwang.clu.handler;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.alonwang.clu.command.CID;
import com.github.alonwang.clu.command.CommandParam;
import com.github.alonwang.clu.command.SID;
import com.github.alonwang.clu.group.GroupManager;
import com.github.alonwang.clu.idiom.IdiomManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.java.Log;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-11-15 14:31
 **/
@Log
public class BusinessHandler extends SimpleChannelInboundHandler<CommandParam> {


    @Override
	protected void channelRead0(ChannelHandlerContext ctx, CommandParam cmd)
			throws Exception {
		int id = cmd.getCid();
        CID cid = CID.valueOf(id);
        if (cid == null) {
			ctx.writeAndFlush(
					new CommandParam(SID.ERROR.value(), "illegal cid"));
        }
        switch (cid) {
            case CONNECT:
                handleConnect(ctx);
                break;
            case ANSWER:
                handleAnswer(ctx, cmd.getBody());
                break;
            default:
			ctx.writeAndFlush(
					new CommandParam(SID.ERROR.value(), "illegal cid"));
        }


    }

    private void handleAnswer(ChannelHandlerContext ctx, String body) {
        String answer = body;
        if (StrUtil.isEmpty(answer) || answer.length() != 4) {
			ctx.writeAndFlush(
					new CommandParam(SID.ERROR.value(), "word length illegal"));
            return;
        }
        boolean correct = IdiomManager.correct(answer);

        int channelId = ctx.channel().hashCode();
        JSONObject result = new JSONObject();
        result.put("correct", correct);
        result.put("id", channelId);
        result.put("word", answer);
		GroupManager.getChannelGroup().writeAndFlush(new CommandParam(
				SID.USER_ANSWER.value(), result.toJSONString()));
        if (correct) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("word", IdiomManager.current());
			GroupManager.getChannelGroup().writeAndFlush(new CommandParam(
					SID.NEW_WORD.value(), jsonObject.toJSONString()));
        }

    }

    private void handleConnect(ChannelHandlerContext ctx) {
        JSONObject jsonObject = new JSONObject();
        List<Integer> channelIds = GroupManager.getChannelGroup().stream().map(Object::hashCode).collect(Collectors.toList());
        jsonObject.put("ids", channelIds);
        jsonObject.put("id", ctx.channel().hashCode());
        jsonObject.put("word", IdiomManager.current());
		ctx.writeAndFlush(new CommandParam(SID.HOMEPAGE_INFO.value(),
				jsonObject.toJSONString()));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        GroupManager.getChannelGroup().add(ctx.channel());
        //推送人员增加
        JSONObject result = new JSONObject();
        result.put("id", ctx.channel().hashCode());
		GroupManager.getChannelGroup().writeAndFlush(new CommandParam(
				SID.NEW_USER_CONNECT.value(), result.toJSONString()));
        // 推送当前所有人,词语


    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 推送用户退出
        removeUserAndNotify(ctx);
    }

    private void removeUserAndNotify(ChannelHandlerContext ctx) {
        GroupManager.getChannelGroup().remove(ctx.channel());
        JSONObject result = new JSONObject();
        result.put("id", ctx.channel().hashCode());
		GroupManager.getChannelGroup().writeAndFlush(new CommandParam(
				SID.USER_DISCONNECT.value(), result.toJSONString()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.warning(ctx.hashCode() + ":" + cause);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            switch (((IdleStateEvent) evt).state()) {
                case READER_IDLE:
                    ctx.writeAndFlush(new PingWebSocketFrame());
                    break;
                default:
                    log.info(ctx.hashCode() + " all idle");
                    break;
            }
        }
    }
}
