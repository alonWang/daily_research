package com.github.alonwang.core.netty.handler;

import com.github.alonwang.core.netty.SessionManager;
import com.github.alonwang.core.netty.Session;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 善后处理:链接关闭,异常捕捉等等
 * @author alonwang
 * @date 2020/10/23 21:56
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class CleanupHandler extends ChannelInboundHandlerAdapter {
    @Autowired
    private SessionManager sessionManager;

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //channel关闭后,移除相关的session
        Session removedSession = sessionManager.removeSession(ctx.channel());
        if (removedSession != null) {
            log.debug("channel close,remove related Session({})", removedSession);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Optional<Session> optSession = sessionManager.getOrCreateSession(ctx.channel());
        if (optSession.isPresent()) {
            log.warn(String.format("channel with session(%s) occur exception,please check.", optSession.get()), cause);
        } else {
            log.warn("channel occur exception,please check.", cause);
        }
    }
}
