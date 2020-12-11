package com.network.netty.bootstrap;

import java.nio.ByteOrder;
import java.util.HashMap;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * Netty Server Bootstrap Factory.
 * @author Hwany 
 */
public class ServerBootstrapFactory implements INettyBootstrapFactory<ServerBootstrap> {

	private NioEventLoopGroup nioAcceptLoopGroup;
	private NioEventLoopGroup nioWorkLoopGroup;
	private HashMap<ChannelOption<?>, Object> channelOptions;
	private HashMap<ChannelOption<?>, Object> childChannelOptions;
	private ChannelInitializer<ServerChannel> channelInitializer;
	
	/**
	 * TCP Constructor	
	 * @param nWorkerThreads
	 * @param channelOptions
	 * @param channelInitializer
	 */
	public ServerBootstrapFactory(NioEventLoopGroup nioAcceptLoopGroup, NioEventLoopGroup nioWorkLoopGroup, HashMap<ChannelOption<?>, Object> channelOptions, ChannelInitializer<ServerChannel> channelInitializer) {
		this.nioAcceptLoopGroup = nioAcceptLoopGroup;
		this.nioWorkLoopGroup = nioWorkLoopGroup;
		this.channelOptions = channelOptions;
		this.channelInitializer = channelInitializer;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ServerBootstrap createBootstrap() {
		
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		
		serverBootstrap.group(this.nioAcceptLoopGroup, this.nioWorkLoopGroup)
					   .channel(NioServerSocketChannel.class)
		               .option(ChannelOption.AUTO_READ, true)
//		               .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 15 * 1000)
		               .childOption(ChannelOption.TCP_NODELAY,true)
		               .childOption(ChannelOption.SO_KEEPALIVE, false)
		               .childHandler(this.channelInitializer);
		
		for (ChannelOption channelOption : channelOptions.keySet()) {
			serverBootstrap.option(channelOption, channelOptions.get(channelOption));
		}


		for (ChannelOption childChannelOption : childChannelOptions.keySet()) {
			serverBootstrap.childOption(childChannelOption, childChannelOptions.get(childChannelOption));
		}
		
		return serverBootstrap;
	}
	
	/**
	 * UDP Constructor
	 */
	public ServerBootstrapFactory(NioEventLoopGroup nioWorkLoopGroup,
								   HashMap<ChannelOption<?>, Object> channelOptions,
								   HashMap<ChannelOption<?>, Object> childChannelOptions,
								   ChannelInitializer<ServerChannel> channelInitializer) {
		this.nioWorkLoopGroup = nioWorkLoopGroup;
		this.channelOptions = channelOptions;
		this.childChannelOptions = childChannelOptions;
		this.channelInitializer = channelInitializer;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Bootstrap createUdpBootstrap() {
		Bootstrap serverBootstrap = new Bootstrap();
		
		serverBootstrap.group(this.nioWorkLoopGroup)
					   .channel(NioDatagramChannel.class)
		               .option(ChannelOption.AUTO_READ, true)
		               .handler(this.channelInitializer);
		
		for (ChannelOption channelOption : channelOptions.keySet()) {
			serverBootstrap.option(channelOption, channelOptions.get(channelOption));
		}
		
		return serverBootstrap;
	}
	
}
