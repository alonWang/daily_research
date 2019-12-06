package com.github.alonwang.clu.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.alonwang.clu.emum.CID;
import com.github.alonwang.clu.factory.CommandFactory;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleStateEvent;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-11-15 14:31
 **/
@Slf4j
@ChannelHandler.Sharable
public class CommandHandler
		extends SimpleChannelInboundHandler<TextWebSocketFrame> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx,
			TextWebSocketFrame msg) throws Exception {
		String text = msg.text();
		log.info("client {} request: {}", ctx.channel().hashCode(), text);
		if (StrUtil.isEmpty(text)) {
			throw new IllegalArgumentException("parameter error");
		}
		JSONObject jsonObject = JSON.parseObject(text);
		Integer cid = jsonObject.getInteger("cid");
		String body = jsonObject.getString("body");
		CID cidEnum = null;
		if (cid == null || (cidEnum = CID.valueOf(cid)) == null) {
			throw new IllegalArgumentException("cid illegal");
		}

		ctx.fireChannelRead(CommandFactory.createCommand(cidEnum, body));
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.fireChannelRead(CommandFactory.createCommand(CID.CONNECT, null));

	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		ctx.fireChannelRead(CommandFactory.createCommand(CID.DISCONNECT, null));

	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
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
