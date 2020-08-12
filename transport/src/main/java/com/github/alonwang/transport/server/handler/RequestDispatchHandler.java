package com.github.alonwang.transport.server.handler;


import com.github.alonwang.transport.core.Context;
import com.github.alonwang.transport.protocol.AbstractRequest;
import com.github.alonwang.transport.server.task.User;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 找到channel对应的User，并将消息分发到业务线程池
 *
 * @author alonwang
 * @date 2020/7/13 16:46
 * @detail
 */
@ChannelHandler.Sharable
public class RequestDispatchHandler extends SimpleChannelInboundHandler<AbstractRequest> {
    private static Map<Channel, User> channel2SessionMap = new ConcurrentHashMap<>();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AbstractRequest msg) throws Exception {
        User user = channel2SessionMap.compute(ctx.channel(), (c, s) -> {
            if (null == s) {
                User createUser = new User();
                createUser.setChannel(c);
                return createUser;
            } else {
                return s;
            }

        });

        Context.getRequestDispatchService().dispatch(user, msg);
    }
}
