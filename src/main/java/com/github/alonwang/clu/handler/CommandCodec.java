package com.github.alonwang.clu.handler;

import com.alibaba.fastjson.JSON;
import com.github.alonwang.clu.command.Command;
import com.github.alonwang.clu.command.SID;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.List;

import cn.hutool.core.util.StrUtil;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-11-26 15:39
 **/
public class CommandCodec extends MessageToMessageCodec<TextWebSocketFrame, Command> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Command msg, List<Object> out) throws Exception {
        out.add(new TextWebSocketFrame(JSON.toJSONString(msg)));
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, TextWebSocketFrame msg, List<Object> out) throws Exception {
        Command command = null;
        String text = msg.text();
        if (!StrUtil.isEmpty(text)) {
            try {
                command = JSON.parseObject(text, Command.class);
            } catch (Exception ignore) {

            }
        }
        if (command != null) {
            out.add(command);
        } else {
            ctx.writeAndFlush(new Command(SID.ERROR.value(), "illegal argument"));
        }
    }
}
