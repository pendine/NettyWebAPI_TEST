package com.network.netty;


import java.net.InetSocketAddress;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.network.netty.bootstrap.INettyBootstrapFactory;
import com.util.ApplicationContextProvider;
import com.util.LogHelper;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;

public class NettyTCPClientPool implements Callable<Object> {

	private static final Logger logger = LoggerFactory.getLogger(NettyTCPClientPool.class);
	ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor)ApplicationContextProvider.getApplicationContext().getBean("writeTaskExecutor");		
	
	private List<String> host = Collections.synchronizedList(new ArrayList<String>());
	private int port;
	private int localPort;
	private int reconnectTime;
	private INettyBootstrapFactory<Bootstrap> nettyBootstrapFactory;
	
//	private List<Channel> clientChannel= Collections.synchronizedList(new ArrayList<Channel>());
	
	private ConcurrentHashMap<Channel, InetSocketAddress> channelMap = new ConcurrentHashMap<Channel, InetSocketAddress>();
	 
	public static ByteOrder readEndian = ByteOrder.LITTLE_ENDIAN;
	public static ByteOrder writeEndian = ByteOrder.LITTLE_ENDIAN;	
	
	// 2019.04.24 dsnoh
//	private List<NettyTCPClient> m_clientList = Collections.synchronizedList(new ArrayList<NettyTCPClient>());
	
	public NettyTCPClientPool(List<String> host, int port, int localPort, INettyBootstrapFactory<Bootstrap> nettyBootstrapFactory, int reconnectTime) {
		this.host = host;
		this.port = port;
		this.localPort = localPort;
		this.nettyBootstrapFactory = nettyBootstrapFactory;
		this.reconnectTime = reconnectTime;
		
		// 2019.04.24 dsnoh
//		for(int i = 0 ; i <this.host.size() ; i++) {
//			if(this.host.get(i).length() <=0) continue; 
//			try {
//				NettyTCPClient n = new NettyTCPClient(this.host.get(i), port, this.nettyBootstrapFactory);
//				this.m_clientList.add(n);
//				executor.submit(this.m_clientList.get(i) );
//			} catch (Exception e2) {
//				logger.error("NettyTCPClientPool >>> {} | ", LogHelper.getPrintStackTrace(e2) ); // 2019.04.24 dsnoh
//			}			
//		}
	}
	
	public NettyTCPClientPool(List<String> host, int port, int localPort, INettyBootstrapFactory<Bootstrap> nettyBootstrapFactory) {
		this(host, port, localPort, nettyBootstrapFactory, 3);
	}
	
	public NettyTCPClientPool(List<String> host, int port, INettyBootstrapFactory<Bootstrap> nettyBootstrapFactory, int reconnectTime) {
		this(host, port, 0, nettyBootstrapFactory, reconnectTime);		
	}
	
	public NettyTCPClientPool(List<String> host, int port, INettyBootstrapFactory<Bootstrap> nettyBootstrapFactory) {
		this(host, port, nettyBootstrapFactory, 3);
	}
	
	@Override
	public Object call() throws Exception {
		
		
		for(int i = 0 ; i <this.host.size() ; i++) {
			if(this.host.get(i).length() <=0) continue; 
			try {
				logger.info("NettyTCPClientPool >>> new NettyTCPClient | host[{}]", this.host.get(i));
				
				NettyTCPClient n = new NettyTCPClient(this.host.get(i), port, this.nettyBootstrapFactory);
				executor.submit(n);
			} catch (Exception e2) {
				//logger.error(LogHelper.getPrintStackTrace(e2));
				
				logger.error("NettyTCPClientPool >>> {} | ", LogHelper.getPrintStackTrace(e2) ); // 2019.04.24 dsnoh
			}
			
		}		
	
		return null;
	}
	
	
	public static ByteOrder getReadEndian() {
		return readEndian;
	}

	public static void setReadEndian(ByteOrder readEndian) {
		NettyTCPClientPool.readEndian = readEndian;
	}

	public static ByteOrder getWriteEndian() {
		return writeEndian;
	}

	public static void setWriteEndian(ByteOrder writeEndian) {
		NettyTCPClientPool.writeEndian = writeEndian;
	}


	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getReconnectTime() {
		return reconnectTime;
	}

	public void setReconnectTime(int reconnectTime) {
		this.reconnectTime = reconnectTime;
	}

	public INettyBootstrapFactory<Bootstrap> getNettyBootstrapFactory() {
		return nettyBootstrapFactory;
	}

	public void setNettyBootstrapFactory(INettyBootstrapFactory<Bootstrap> nettyBootstrapFactory) {
		this.nettyBootstrapFactory = nettyBootstrapFactory;
	}

	public ConcurrentHashMap<Channel, InetSocketAddress> getChannelMap() {
		return channelMap;
	}

	public void setChannelMap(ConcurrentHashMap<Channel, InetSocketAddress> channelMap) {
		this.channelMap = channelMap;
	}


	
}