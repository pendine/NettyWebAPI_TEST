package com.network.netty.bootstrap;

import java.util.HashMap;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ApplicationContextProvider;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * Netty Client Bootstrap Factory.
 * TCP/UDP ChannelType = io.netty.channel.socket.nio.NioSocketChannel(TCP) or io.netty.channel.socket.nio.NioDatagramChannel(UDP).
 * @author Hwany 
 */
public class OPBootstrapFactory implements INettyBootstrapFactory<Bootstrap> {

	private static final Logger logger = LoggerFactory.getLogger(OPBootstrapFactory.class);
//	private NioEventLoopGroup nioEventLoopGroup;
//	private EventLoopGroup  nioEventLoopGroup;	
	private String workerThread;
	private Class<? extends Channel> channelType;
	private HashMap<ChannelOption<?>, Object> channelOptions;
	private ChannelInitializer<?> channelInitializer;
	
	
	Properties contextProperties = (Properties)ApplicationContextProvider.getApplicationContext().getBean("contextProperties");		
	/**
	 * TCP Constructor	
	 * @param nWorkerThreads
	 * @param channelOptions
	 * @param channelInitializer
	 */
	/*public NettyClientBootstrapFactory(EventLoopGroup nioEventLoopGroup, HashMap<ChannelOption<?>, Object> channelOptions, ChannelInitializer<?> channelInitializer) {
		this(nioEventLoopGroup, NioSocketChannel.class, channelOptions, channelInitializer);
	}*/
	public OPBootstrapFactory(String workerThread, HashMap<ChannelOption<?>, Object> channelOptions, ChannelInitializer<?> channelInitializer) {
		this(workerThread, NioSocketChannel.class, channelOptions, channelInitializer);
	}
	/**
	 * TCP/UDP Constructor	
	 * @param nWorkerThreads
	 * @param channelOptions
	 * @param channelInitializer
	 */	
	public OPBootstrapFactory(String workerThread, Class<? extends Channel> channelType, HashMap<ChannelOption<?>, Object> channelOptions, ChannelInitializer<?> channelInitializer) {
		this.workerThread = workerThread;
		this.channelType = channelType;
		this.channelOptions = channelOptions;
		this.channelInitializer = channelInitializer;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Bootstrap createBootstrap() throws Exception{
		
		Bootstrap clientBootstrap = new Bootstrap();
		
		EventLoopGroup e = new NioEventLoopGroup(Integer.valueOf(workerThread));
		logger.info(" >>> Start Create BootStrap...");
		clientBootstrap.group(e)
		               .channel(this.channelType)
		               .option(ChannelOption.AUTO_READ, true)
		               .handler(this.channelInitializer);
		
		for (ChannelOption channelOption : channelOptions.keySet()) {
			clientBootstrap.option(channelOption, channelOptions.get(channelOption));
		}
		logger.info(" >>> Done Create BootStrap...");
		return clientBootstrap;
	}
	
}
