package com.github.alonwang.clu.handler;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.github.alonwang.clu.command.CommandParam;
import com.github.alonwang.clu.command.SID;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.java.Log;

import java.util.List;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-11-26 15:39
 **/
@Log
public class CommandCodec
		extends MessageToMessageCodec<TextWebSocketFrame, CommandParam> {
    @Override
	protected void encode(ChannelHandlerContext ctx, CommandParam msg,
			List<Object> out) throws Exception {
        log.info("send " + ctx.hashCode() + " " + JSON.toJSONString(msg));
        out.add(new TextWebSocketFrame(JSON.toJSONString(msg)));
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, TextWebSocketFrame msg, List<Object> out) throws Exception {
		CommandParam commandParam = null;
        String text = msg.text();
        if (!StrUtil.isEmpty(text)) {
            try {
				commandParam = JSON.parseObject(text, CommandParam.class);
            } catch (Exception ignore) {

            }
        }
		if (commandParam != null) {
			out.add(commandParam);
        } else {
			ctx.writeAndFlush(
					new CommandParam(SID.ERROR.value(), "illegal argument"));
        }
    }
}
