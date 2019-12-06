package com.github.alonwang.clu.handler;

import com.github.alonwang.clu.exception.BusinessException;
import com.github.alonwang.clu.util.ExceptionUtil;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-12-05 17:16
 **/
@Slf4j
@ChannelHandler.Sharable
public class ExceptionHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {

		if (cause instanceof BusinessException) {
			log.info("client {}  exception: {}", ctx.channel().hashCode(),
					cause);
			ExceptionUtil.throwException(ctx, cause.toString());
		} else {
			log.error("client {}  exception: {}", ctx.channel().hashCode(),
					cause);
			ExceptionUtil.throwException(ctx, cause.toString());
		}
	}
}
