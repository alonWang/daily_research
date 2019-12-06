package com.github.alonwang.clu;

import com.github.alonwang.clu.command.CommandResp;
import com.github.alonwang.clu.emum.SID;
import com.github.alonwang.clu.group.GroupManager;
import com.github.alonwang.clu.handler.BusinessHandler;
import com.github.alonwang.clu.handler.CommandEncoder;
import com.github.alonwang.clu.handler.CommandHandler;
import com.github.alonwang.clu.handler.ExceptionHandler;
import com.github.alonwang.clu.idiom.IdiomManager;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.lang.Singleton;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-11-15 08:47
 **/
@Slf4j
public class CluServerStarter {
    private static final ScheduledExecutorService executorService = Executors
            .newSingleThreadScheduledExecutor();
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(2);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(10);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new ChannelHandler() {
                    @Override
                    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
                        IdiomManager.init();
                        //定期更换成语
						executorService.scheduleAtFixedRate(() -> {
                            if (IdiomManager.idle()) {
                                IdiomManager.next(IdiomManager.current());
                                GroupManager.channelGroup()
                                        .writeAndFlush(CommandResp.newInstance(
                                                SID.NEW_WORD.value(),
                                                IdiomManager.current()));
                            }
                            IdiomManager.resetIdle();
                        }, 30, 30, TimeUnit.SECONDS);
                    }

                    @Override
                    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
                        log.info(channelHandlerContext.name() + " handler removed");
                    }

                    @Override
                    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {
                        log.info(channelHandlerContext.name() + " handler exception " + throwable);
                    }
                }).childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                nioSocketChannel.pipeline().addLast(
                        new IdleStateHandler(5, 0, 0),
                        new HttpServerCodec(),
                        new HttpObjectAggregator(65536),
                        new WebSocketServerProtocolHandler("/"),
                        Singleton.get(CommandEncoder.class),
                        Singleton.get(CommandHandler.class),
                        Singleton.get(BusinessHandler.class),
                        Singleton.get(ExceptionHandler.class)
                );
            }
        });

        serverBootstrap.bind(8080);
    }
}
