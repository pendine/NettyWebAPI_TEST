package com.network.netty;


import java.net.InetSocketAddress;
import java.nio.ByteOrder;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netty.initializer.Client_Initializer;
import com.netty.initializer.NettyWebClientChannelInit;
import com.network.netty.bootstrap.INettyBootstrapFactory;
import com.network.netty.listener.CloseFutureListener;
import com.network.netty.listener.ConnectFutureListener;
import com.util.LogHelper;
import com.util.NettyHelper;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder;

	
public class NettyWebClient {
	
	private static final Logger logger = LoggerFactory.getLogger(NettyWebClient.class);
	
	ChannelFuture cf;
	Bootstrap b;
	EventLoopGroup group;
    
	String host;
	int port;
	int localPort;
//    INettyBootstrapFactory<Bootstrap> nettyBootstrapFactory;
	int reconnectTime;

	Bootstrap clientBootstrap;

    public NettyWebClient(String host, int port, int localPort, INettyBootstrapFactory<Bootstrap> nettyBootstrapFactory, int reconnectTime) {
		this.host = host;
		this.port = port;
		this.localPort = localPort;
//		this.nettyBootstrapFactory = nettyBootstrapFactory;
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
	
	public NettyWebClient(String host, int port, int localPort, INettyBootstrapFactory<Bootstrap> nettyBootstrapFactory) {
		this(host, port, localPort, nettyBootstrapFactory, 1);
	}
	
	public NettyWebClient(String host, int port, INettyBootstrapFactory<Bootstrap> nettyBootstrapFactory, int reconnectTime) 
	{
		this(host, port, 0, nettyBootstrapFactory, reconnectTime);		
	}
	
	public NettyWebClient(String host, int port, INettyBootstrapFactory<Bootstrap> nettyBootstrapFactory) 
	{
		this(host, port, nettyBootstrapFactory, 1);
	}
	
    public void connect() 
	{
    	group = new NioEventLoopGroup();
        try 
        {
//            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new Client_Initializer() );
            System.out.println(" 커넥션 확인전sout ");
            System.out.println("HOST : " + host + " PORT : "+port);
            System.out.println(" 커넥션 확인용 sout ");
//	            cf.channel().closeFuture().sync();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
	    
    public void createRequest(String host, int port, String url) throws Exception 
    {
    	HttpRequest request = null;
    	HttpPostRequestEncoder postRequestEncoder = null;
        
    	request = new DefaultFullHttpRequest( HttpVersion.HTTP_1_1, HttpMethod.GET, url , Unpooled.EMPTY_BUFFER );
        request.headers().set( HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED );
        request.headers().set( HttpHeaderNames.HOST, host+":"+port );
        request.headers().set( HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE );
//      request.headers().set( HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderValues.GZIP );
        
        group = new NioEventLoopGroup();
        
//        clientBootstrap.group(group).channel(NioSocketChannel.class)//.option(ChannelOption.TCP_NODELAY, true)
//        .handler(new NettyWebClientChannelInit(group) );
        
        System.out.println("requset host : " + host );
        System.out.println("requset port : " + port );
        System.out.println("requset url : "	 + url );
        System.out.println("set url : " + request.uri());
        
//        Channel ch = b.connect(host, port).sync().channel();
        
        this.cf = b.connect(host, port);
            
        System.out.println("channelFuture is Done : "		+ cf.isDone());
        System.out.println("channelFuture is Success : "	+ cf.isSuccess());
        System.out.println("channelFuture is Cancelled : "	+ cf.isCancelled());
        System.out.println("channelFuture is Void : "		+ cf.isVoid());
            
//        postRequestEncoder = new HttpPostRequestEncoder(request, false);
        
//        postRequestEncoder.addBodyAttribute("url", url);
        
//        request = postRequestEncoder.finalizeRequest();
//        postRequestEncoder.close();
        cf.channel().writeAndFlush(request);
            
    }
    
    public void close() 
    {
        cf.channel().close();
        group.shutdownGracefully();
    }
    
    
    public ChannelFuture getChannelFuture() 
    {
    	return this.cf;
    }

}
