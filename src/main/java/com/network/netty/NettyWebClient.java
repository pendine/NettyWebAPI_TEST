package com.network.netty;


import java.net.InetSocketAddress;
import java.net.URI;
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
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

public class NettyWebClient {
	
	private static final Logger logger = LoggerFactory.getLogger(NettyWebClient.class);
	
	ChannelFuture cf;
	Bootstrap b;
	EventLoopGroup group;
	
	SslContext sslCtx = null;
	
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
		
		try {
			clientBootstrap = nettyBootstrapFactory.createBootstrap();
			logger.info("netty Web 클라이언트 nettyBootstrapFactory.createBootstr 생성");
		
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
	
	 public void createRequest(String host, String port ) throws Exception
	 {
		 createRequest(host, port, 900);
	 }
	    
    public void createRequest(String host, String portStr, int keepAliveTimeOut ) throws Exception 
    {
    	HttpRequest request = null;
        
    	System.out.println("input host : " + host);
    	System.out.println("input port : " + portStr);
    	
    	String url = "http://"+host+":"+portStr + "/users/login";
    	
    	URI uri = new URI(url);
    	
    	request = new DefaultFullHttpRequest( 
    			HttpVersion.HTTP_1_1
    			, HttpMethod.GET
    			, url
    			);
        
        System.out.println("requset host : " + host );
        System.out.println("requset port : " + portStr );
        System.out.println("set url : " + request.uri());
        
//        String scheme = uri.getScheme() == null? "http" : uri.getScheme();
//        String address = uri.getHost() == null? "127.0.0.1" : uri.getHost();
                
        port = uri.getPort();
        System.out.println("PORT : " + port );
//        if (port == -1) 
//        {
//            if ("http".equalsIgnoreCase(scheme)) 
//            {
//                port = 80;
//            } 
//            else if ("https".equalsIgnoreCase(scheme)) 
//            {
//                port = 443;
//            }
//        }
        System.out.println("PORT : " + port );
        
//        System.out.println("scheme : " + scheme );
//        System.out.println("address : " + address );
        System.out.println("getPath : " + uri.getPath() );
        System.out.println("getQuery : " + uri.getQuery() );

        System.out.println("toASCIIString : " + uri.toASCIIString());
        System.out.println("toString : " + uri.toString());
        
//        final boolean ssl = "https".equalsIgnoreCase(scheme);
//        if (ssl) 
//        {
//            sslCtx = SslContextBuilder
//	            		.forClient()
//	            		.trustManager(InsecureTrustManagerFactory.INSTANCE)
//	            		.build();
//        } 
//        else 
//        {
//            sslCtx = null;
//        }

        group = new NioEventLoopGroup();
        
        this.cf = b.connect(host, port);
        Channel ch = this.cf.sync().channel();
    	
        System.out.println("channelFuture is Done : "		+ cf.isDone());
        System.out.println("channelFuture is Success : "	+ cf.isSuccess());
        System.out.println("channelFuture is Cancelled : "	+ cf.isCancelled());
        System.out.println("channelFuture is Void : "		+ cf.isVoid());
        
        ch.writeAndFlush(request);
        
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
    
    public SslContext getSslContext() 
    {
    	return this.sslCtx;
    }

}
