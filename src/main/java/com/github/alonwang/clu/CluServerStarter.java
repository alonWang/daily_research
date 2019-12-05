package com.github.alonwang.clu;

import com.alibaba.fastjson.JSONObject;
import com.github.alonwang.clu.command.CommandParam;
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

import java.util.concurrent.TimeUnit;

import lombok.extern.java.Log;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-11-15 08:47
 **/
@Log
public class CluServerStarter {
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(2);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(10);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new ChannelHandler() {
                    @Override
                    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
                        log.info(channelHandlerContext.name() + " handler add");
                        IdiomManager.init();
                        //定期更换成语
                        channelHandlerContext.channel().eventLoop().scheduleAtFixedRate(() -> {
                            if (IdiomManager.idle()) {
                                IdiomManager.next(IdiomManager.current());
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("word", IdiomManager.current());
                                GroupManager.channelGroup()
                                        .writeAndFlush(new CommandParam(
                                                SID.NEW_WORD.value(),
                                                jsonObject
                                                        .toJSONString()));
                            }
                            IdiomManager.resetIdle();
								}, 60, 60, TimeUnit.SECONDS);
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
                        new CommandEncoder(),
                        new CommandHandler(),
                        new BusinessHandler(),
                        new ExceptionHandler()

                );
            }
        });

        serverBootstrap.bind(8080);
    }
}
