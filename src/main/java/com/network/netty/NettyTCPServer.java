package com.network.netty;


import java.nio.ByteOrder;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;


public class NettyTCPServer implements Callable<Object> {

	private static final Logger logger = LoggerFactory.getLogger(NettyTCPServer.class);
	
	private int port;

	private ServerBootstrap serverBootstrap;
	private Channel serverChannel;
	
	public static ByteOrder readEndian = ByteOrder.LITTLE_ENDIAN;
	public static ByteOrder writeEndian = ByteOrder.LITTLE_ENDIAN;	
	
	public NettyTCPServer(int port, ServerBootstrap serverBootstrap) {
		this.port = port;
		this.serverBootstrap = serverBootstrap;
	}
	
	@Override
	public Object call() throws Exception {
		logger.info("TCP Server Start .. Port[{}]...", port);
		
		this.serverChannel = this.serverBootstrap.bind(this.port).sync().channel();
		
		if(this.serverChannel.isActive()) logger.info("TCP Server Start | Port[{}] ... OK", port);
		else 							logger.info("TCP Server Start | Port[{}] ... Fail", port);
		
		this.serverChannel.closeFuture().await();
		
		return serverChannel;
	}
	
	

	public static ByteOrder getReadEndian() {
		return readEndian;
	}

	public static void setReadEndian(ByteOrder readEndian) {
		NettyTCPServer.readEndian = readEndian;
	}

	public static ByteOrder getWriteEndian() {
		return writeEndian;
	}

	public static void setWriteEndian(ByteOrder writeEndian) {
		NettyTCPServer.writeEndian = writeEndian;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public ServerBootstrap getServerBootstrap() {
		return serverBootstrap;
	}

	public Channel getServerChannel() {
		return serverChannel;
	}

	public void setServerChannel(Channel serverChannel) {
		this.serverChannel = serverChannel;
	}
	
}