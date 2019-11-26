package com.github.alonwang.clu;

import com.github.alonwang.clu.handler.BusinessHandler;
import com.github.alonwang.clu.handler.CommandCodec;
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

import lombok.extern.java.Log;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-11-15 08:47
 **/
@Log
public class CluServerStarter {
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(10);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new ChannelHandler() {
                    @Override
                    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
                        log.info(channelHandlerContext.name() + " handler add");
                        IdiomManager.init();
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
                        new HttpServerCodec(),
                        new HttpObjectAggregator(65536),
                        new WebSocketServerProtocolHandler("/"),
                        new CommandCodec(),
                        new BusinessHandler()

                );
            }
        });

        serverBootstrap.bind(8080);
    }
}
