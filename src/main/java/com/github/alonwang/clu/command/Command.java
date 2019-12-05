package com.github.alonwang.clu.command;

import io.netty.channel.ChannelHandlerContext;

public interface Command {

	void execute(ChannelHandlerContext ctx);
}
