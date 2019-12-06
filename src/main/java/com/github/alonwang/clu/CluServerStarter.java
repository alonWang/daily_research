package com.github.alonwang.clu;

import com.github.alonwang.clu.handler.BusinessHandler;
import com.github.alonwang.clu.handler.CommandEncoder;
import com.github.alonwang.clu.handler.CommandHandler;
import com.github.alonwang.clu.handler.ExceptionHandler;
import com.github.alonwang.clu.manager.IdiomManager;
import com.github.alonwang.clu.manager.IdleManager;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.IdleStateHandler;

import cn.hutool.core.lang.Singleton;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-11-15 08:47
 **/
@Slf4j
public class CluServerStarter {

	public static void main(String[] args) {
		NioEventLoopGroup bossGroup = new NioEventLoopGroup(2);
		NioEventLoopGroup workerGroup = new NioEventLoopGroup(10);
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		serverBootstrap.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<NioSocketChannel>() {
					@Override
					protected void initChannel(
							NioSocketChannel nioSocketChannel)
							throws Exception {
						nioSocketChannel.pipeline().addLast(
								new IdleStateHandler(5, 0, 0),
								new HttpServerCodec(),
								new HttpObjectAggregator(65536),
								new WebSocketServerProtocolHandler("/"),
								Singleton.get(CommandEncoder.class),
								Singleton.get(CommandHandler.class),
								Singleton.get(BusinessHandler.class),
								Singleton.get(ExceptionHandler.class));
					}
				});

		serverBootstrap.bind(8080).addListener(future -> {
			if (future.isSuccess()) {
				IdiomManager.init();
				IdleManager.init();
			}
		});
	}
}
