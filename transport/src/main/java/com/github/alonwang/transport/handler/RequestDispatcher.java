package com.github.alonwang.transport.handler;


import com.google.common.collect.BiMap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Request分发器,将Request分发到业务线程池中
 *
 * @author alonwang
 * @date 2020/7/13 16:46
 * @detail
 */
@ChannelHandler.Sharable
public class RequestDispatcher extends SimpleChannelInboundHandler<Object> {
    //消息分发线程池
    ExecutorService executorService= Executors.newFixedThreadPool(1);
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {


    }
}
