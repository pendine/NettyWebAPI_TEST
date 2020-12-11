package com.network.netty.bootstrap;

import java.util.HashMap;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ApplicationContextProvider;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
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
public class ClientBootstrapFactory implements INettyBootstrapFactory<Bootstrap> 
{

	private static final Logger logger = LoggerFactory.getLogger(ClientBootstrapFactory.class);
	private String workerThread;
	private EventLoopGroup nioEventLoopGroup;
	private Class<? extends Channel> channelType;
	private HashMap<ChannelOption<?>, Object> channelOptions;
	private HashMap<ChannelOption<?>, Object> childChannelOptions;
	private ChannelInitializer<?> channelInitializer;
	public String host;
	public int port;
	
	Properties contextProperties = (Properties) ApplicationContextProvider.getApplicationContext().getBean("contextProperties");		

	/**
	 * TCP Constructor	
	 * @param nWorkerThreads
	 * @param channelOptions
	 * @param channelInitializer
	 */
	
	public void setConnect(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	
	public ClientBootstrapFactory(EventLoopGroup nioEventLoopGroup, HashMap<ChannelOption<?>, Object> channelOptions, ChannelInitializer<?> channelInitializer) {
//		this(nioEventLoopGroup, NioSocketChannel.class, channelOptions, channelInitializer);
//		this.workerThread = "1";
		this.nioEventLoopGroup = nioEventLoopGroup;
		this.channelOptions = channelOptions;
		this.channelInitializer = channelInitializer;
	}
	public ClientBootstrapFactory(String workerThread, HashMap<ChannelOption<?>, Object> channelOptions, ChannelInitializer<?> channelInitializer) {
		this(workerThread, NioSocketChannel.class, channelOptions, channelInitializer);
	}
	
	
	/**
	 * TCP/UDP Constructor	
	 * @param nWorkerThreads
	 * @param channelOptions
	 * @param channelInitializer
	 */	
	
	
	public ClientBootstrapFactory(String workerThread, 
									Class<? extends Channel> channelType, 
									HashMap<ChannelOption<?>, Object> channelOptions, 
									ChannelInitializer<?> channelInitializer) {
		this.workerThread = workerThread;
		this.channelType = channelType;
		this.channelOptions = channelOptions;
		this.channelInitializer = channelInitializer;
	}


	public ClientBootstrapFactory(String workerThread, 
									Class<? extends Channel> channelType, 
									HashMap<ChannelOption<?>, Object> channelOptions, 
									ChannelInitializer<?> channelInitializer,
									HashMap<ChannelOption<?>, Object> childChannelOptions) {
		this.workerThread = workerThread;
		this.channelType = channelType;
		this.channelOptions = channelOptions;
		this.channelInitializer = channelInitializer;
		this.childChannelOptions = childChannelOptions;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Bootstrap createBootstrap() throws Exception{
		
		Bootstrap clientBootstrap = new Bootstrap();
		
//		if(!workerThread.equals(null)) {
//			EventLoopGroup e = new NioEventLoopGroup(Integer.valueOf(workerThread));
//			System.out.println(workerThread);
//		}
		logger.info(" >>> Start Create BootStrap...");
		clientBootstrap.group(this.nioEventLoopGroup)
//						.remoteAddress(host,port)
						.channel(NioSocketChannel.class)
						.option(ChannelOption.AUTO_READ, true)
						.handler(this.channelInitializer);
		
		for (ChannelOption channelOption : channelOptions.keySet()) {
			clientBootstrap.option(channelOption, channelOptions.get(channelOption));
		}
		
//		ChannelFuture f = clientBootstrap.connect(host, port).sync();
//		f.channel().closeFuture().sync();
		
		logger.info(" >>> Done Create BootStrap...");
		return clientBootstrap;
	}
	
	
	//create bootstrap method backup source
	/*
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
		
//		ChannelFuture cf = clientBootstrap.connect(HOST, PORT).sync();
//		cf.channel().closeFuture().sync();
		
		logger.info(" >>> Done Create BootStrap...");
		return clientBootstrap;
	}
	*/
	
}
