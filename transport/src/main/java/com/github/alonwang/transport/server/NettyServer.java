package com.github.alonwang.transport.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author alonwang
 * @date 2020/7/13 15:09
 * @detail
 */
@Slf4j
public class NettyServer {
    public void start(int port, ChannelInitializer<SocketChannel> initializer) throws Exception {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    //ChannelOption的含义
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .childHandler(initializer);
            serverBootstrap.bind(port).sync();
        } catch (Exception e) {
            //为什么需要去关闭？ 它是什么时候被启动的
            boss.shutdownGracefully();
            worker.shutdownGracefully();
            throw e;
        }
        log.info("NettyServer start at {}",port);

    }
}
