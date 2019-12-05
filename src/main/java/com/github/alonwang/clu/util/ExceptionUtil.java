package com.github.alonwang.clu.util;

import com.github.alonwang.clu.command.CommandResp;
import com.github.alonwang.clu.emum.SID;

import io.netty.channel.ChannelHandlerContext;

public class ExceptionUtil {
	public static void throwException(ChannelHandlerContext ctx, String msg) {
		ctx.writeAndFlush(new CommandResp(SID.BUSINESS_EXCEPTION.value(), msg));
	}

	public static void throwError(ChannelHandlerContext ctx, String msg) {
		ctx.writeAndFlush(new CommandResp(SID.SYSTEM_ERROR.value(), msg));
	}
}
