package com.github.alonwang.clu.handler;

import com.github.alonwang.clu.exception.BusinessException;
import com.github.alonwang.clu.util.ExceptionUtil;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import lombok.extern.java.Log;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-12-05 17:16
 **/
@Log
@ChannelHandler.Sharable
public class ExceptionHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info(cause.toString());
        if (cause instanceof BusinessException) {
            ExceptionUtil.throwException(ctx, cause.toString());
        } else {
            ExceptionUtil.throwException(ctx, cause.toString());
        }
    }
}
