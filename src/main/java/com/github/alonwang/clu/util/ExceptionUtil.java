package com.github.alonwang.clu.util;

import com.github.alonwang.clu.command.CommandResp;
import com.github.alonwang.clu.command.SID;
import io.netty.channel.ChannelHandlerContext;

public class ExceptionUtil {
	public static void throwException(ChannelHandlerContext ctx, String msg) {
		ctx.write(new CommandResp(SID.ERROR.value(), msg));
	}
}
