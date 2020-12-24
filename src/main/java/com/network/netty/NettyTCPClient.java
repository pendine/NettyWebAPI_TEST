package com.network.netty;


import java.net.InetSocketAddress;
import java.nio.ByteOrder;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.network.netty.bootstrap.INettyBootstrapFactory;
import com.network.netty.listener.CloseFutureListener;
import com.network.netty.listener.ConnectFutureListener;
import com.util.LogHelper;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;


public class NettyTCPClient implements Callable<Object> {
	
	private static final Logger logger = LoggerFactory.getLogger(NettyTCPClient.class);
	
	private String host;
	private int port;
	private int localPort;
	private int reconnectTime;
	private INettyBootstrapFactory<Bootstrap> nettyBootstrapFactory;
	
	
	private Channel clientChannel;

	Bootstrap clientBootstrap = null; // 2019.04.24 dsnoh
	private ChannelFuture connectChannelFuture; // 2019.04.24 dsnoh
	
	
	private ConcurrentHashMap<Channel, InetSocketAddress> channelMap = new ConcurrentHashMap<Channel, InetSocketAddress>();
	
	public static ByteOrder readEndian = ByteOrder.LITTLE_ENDIAN;
	public static ByteOrder writeEndian = ByteOrder.LITTLE_ENDIAN;	

	
	public NettyTCPClient(String host, int port, int localPort, INettyBootstrapFactory<Bootstrap> nettyBootstrapFactory, int reconnectTime) {
		this.host = host;
		this.port = port;
		this.localPort = localPort;
		this.nettyBootstrapFactory = nettyBootstrapFactory;
		this.reconnectTime = reconnectTime;
		
		// 2019.04.24 dsnoh
		try {
//			logger.info("netty 클라이언트 생성");
			clientBootstrap = nettyBootstrapFactory.createBootstrap();
//			clientBootstrap.remoteAddress( new InetSocketAddress(this.host, this.port) );
//			logger.info(" 호스트 : "+ this.host + " 포트번호 : "+ this.port);
//			this.clientBootstrap.connect(this.host, this.port);
//			logger.info(" 부트스트랩 상태 : "+ clientBootstrap.connect(this.host, this.port));
			
//			this.channelFuture = clientBootstrap.connect(host, port).sync();
//			this.channelFuture.channel().closeFuture().sync();
//			this.returnChannel = this.channelFuture.channel();
		
		}catch(Exception e) {
			logger.error("Fail to Create Bootstrap | {}",LogHelper.getPrintStackTrace(e));
		}
	}
	
	public NettyTCPClient(String host, int port, int localPort, INettyBootstrapFactory<Bootstrap> nettyBootstrapFactory) {
		this(host, port, localPort, nettyBootstrapFactory, 1);
	}
	
	public NettyTCPClient(String host, int port, INettyBootstrapFactory<Bootstrap> nettyBootstrapFactory, int reconnectTime) {
		this(host, port, 0, nettyBootstrapFactory, reconnectTime);		
	}
	
	public NettyTCPClient(String host, int port, INettyBootstrapFactory<Bootstrap> nettyBootstrapFactory) {
		this(host, port, nettyBootstrapFactory, 1);
	}
	
	// NettyTCPClient 생성자---------------------------------
	
	//재접속시 Bootstrap 을 채널이 CLOSE 될때마다 새로 만들것이냐 아님 처음 만든걸로 계속 쓸것이냐...
	//새로만들때에는 기존 BOOTSTRAP .GROUP.SHUTDOWN..으로 제거해야될것인가.. 현 소스에서는 제거가 없음...
	@Override
	public Object call() throws Exception {
		try {
			InetSocketAddress addr =  new InetSocketAddress(this.host, this.port);

			logger.info("NettyTCPClient call() | Start {}:{} | localPort[{}]", this.host, this.port, this.localPort); // 2019.04.24 dsnoh
			logger.info("NettyTCPClient call() | target ip : {} | port : {}", host, port);
			
//			connectChannelFuture = null;
			connectChannelFuture = this.clientBootstrap.connect(addr);
			
			logger.info("NettyTCPClient call() | clientBootstrap.connect() :  " + this.clientBootstrap.toString() );
			
			logger.info("NettyTCPClient call() | connectChannelFuture : {}", connectChannelFuture.toString() ); // 2019.04.24 dsnoh
			logger.info("NettyTCPClient call() | connectChannelFuture.hashCode : {}", connectChannelFuture.hashCode() ); // 2019.04.24 dsnoh
			logger.info("NettyTCPClient call() | clientBootstrap.connect start : {}", addr.toString() ); // 2019.04.24 dsnoh
			
			logger.info("NettyTCPClient call() | netty tcp client connectChannelFuture.isDone()  : "+ connectChannelFuture.isDone() );
			logger.info("NettyTCPClient call() | netty tcp client connectChannelFuture.isSuccess()  : "+ connectChannelFuture.isSuccess() );
			logger.info("NettyTCPClient call() | netty tcp client connectChannelFuture.isCancellable()  : "+ connectChannelFuture.isCancellable() );
			logger.info("NettyTCPClient call() | netty tcp client connectChannelFuture.isCancelled()  : "+ connectChannelFuture.isCancelled() );
			logger.info("NettyTCPClient call() | netty tcp access info : " + addr.toString());
			logger.info("NettyTCPClient call() | netty tcp client channel future : "+ this.clientChannel);

			
			clientChannel = connectChannelFuture
					.addListener(new ConnectFutureListener(this)).sync().channel().closeFuture()
					.addListener(new CloseFutureListener(this)	).sync().channel();
//					.addListener(new ConnectFutureListener(this)).sync().channel();
			
			logger.info("NettyTCPClient call() | connectChannelFuture.addListener END : {}", clientChannel.toString() ); // 2019.04.24 dsnoh
						
		}
		catch (Exception e) 
		{
			logger.error("NettyTCPClient call() | Exception | NettyTCPClient >>> {} | ", e.getMessage());
			if (this.clientChannel != null)
			{
//				this.clientChannel = null;
				logger.info("NettyTCPClient call() | Exception | Host IP: {} | 채널 널값 아님 " , host);
				this.clientChannel.flush();
				this.clientChannel.close().awaitUninterruptibly();
//				this.clientChannel.close();
				this.call();
			}
			else
			{
				logger.info("NettyTCPClient call() | Exception | Host IP: {} | 채널 널값" , host);
				try 
				{
					this.clientChannel.flush();
					this.clientChannel.close().awaitUninterruptibly();
//					this.clientChannel.close();
				}
				catch(Exception exception)
				{
					logger.info("NettyTCPClient call() | Exception | 채널 널값에서 채널을 비우거나 닫을 수 없음");
					exception.printStackTrace();
				}
				
				Thread.sleep(1000);
				this.call();
			}
			
			logger.error("NettyTCPClient call() | Exception | NettyTCP Exception : {} | netty tcp client 클래스에서 오류남 이거 뭔지 파악해야함" , LogHelper.getPrintStackTrace(e) ); 

		}
		finally
		{

		}
		return null;
	}
	
	
	public static ByteOrder getReadEndian() {
		return readEndian;
	}

	public static void setReadEndian(ByteOrder readEndian) {
		NettyTCPClient.readEndian = readEndian;
	}

	public static ByteOrder getWriteEndian() {
		return writeEndian;
	}

	public static void setWriteEndian(ByteOrder writeEndian) {
		NettyTCPClient.writeEndian = writeEndian;
	}

	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	public String getHost() {
		return this.host;
	}
	public void setHost(String ip) {
		this.host = ip;
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

	public Channel getChannel() {
		return this.clientChannel;
	}
	
	public ChannelFuture getChannelFuture() {
		return this.connectChannelFuture;
	}
	
	public void setChannelFuture(ChannelFuture ch) {
		this.connectChannelFuture = ch;
	}
	
	public Bootstrap getBootstrap() {
		return this.clientBootstrap;
	}
	public void setBootstrap(Bootstrap b) {
		this.clientBootstrap = b;
	}
	
}