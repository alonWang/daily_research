package com.github.alonwang.clu.handler;

import com.alibaba.fastjson.JSON;
import com.github.alonwang.clu.command.CommandResp;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-11-26 15:39
 **/
@Slf4j
@ChannelHandler.Sharable
public class CommandEncoder extends MessageToMessageEncoder<CommandResp> {
	@Override
	protected void encode(ChannelHandlerContext ctx, CommandResp msg,
			List<Object> out) throws Exception {
		log.info("client {} response: {}", ctx.channel().hashCode(), msg);
		out.add(new TextWebSocketFrame(JSON.toJSONString(msg)));
	}

}
