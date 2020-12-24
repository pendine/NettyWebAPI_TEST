package com.network.netty.listener;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.network.netty.NettyTCPClient;
import com.util.LogHelper;
import com.util.NettyHelper;

public class CloseFutureListener  implements ChannelFutureListener// , ChannelInboundHandler
{
	
	private static final Logger logger = LoggerFactory.getLogger(CloseFutureListener.class);

	private final NettyTCPClient nettyClient;
	
	public CloseFutureListener(NettyTCPClient nettyClient) {
		this.nettyClient = nettyClient;
	}
	
	@Override
	public void operationComplete(ChannelFuture channelFuture)  {

		String ip = NettyHelper.getRemoteAddress(channelFuture.channel());
		int port = NettyHelper.getRemotePort(channelFuture.channel());
		try 
		{

//			String pisID = pisManager.getPISTemplateByIpPort(ip, port).getPIS_ID();
			
			logger.info("CloseFutureListener.ChannelFuture.isDone()  : {} ", channelFuture.isDone() );
			logger.info("CloseFutureListener.ChannelFuture.isSuccess()  : {}",  channelFuture.isSuccess() );
			logger.info("CloseFutureListener.ChannelFuture.isCancellable()  : {}",  channelFuture.isCancellable() );
			logger.info("CloseFutureListener.ChannelFuture.isCancelled()  : {}", channelFuture.isCancelled() );
			logger.info("CloseFutureListener.channelFuture.channel().isActive()  : {}", channelFuture.channel().isActive());
//			logger.info("CloseFutureListener.channelFuture.channel().disconnect()  : {}",  channelFuture.channel().disconnect());
			logger.info("CloseFutureListener.channelFuture.channel().isOpen()  : {}",  channelFuture.channel().isOpen());
			logger.info("CloseFutureListener.channelFuture.channel().isRegistered()  : {}", channelFuture.channel().isRegistered());
			logger.info("CloseFutureListener.channelFuture.channel().isWritable()  : {}",  channelFuture.channel().isWritable());
			
			logger.info("Close Future | Success | Retry Connect | Channel Close | {} "
					, channelFuture.channel().toString()
					);
			
//			channelFuture.channel().flush();
//			channelFuture.channel().close().awaitUninterruptibly();
			
			channelFuture.channel().eventLoop().schedule(nettyClient, nettyClient.getReconnectTime() , TimeUnit.SECONDS);
			
		}
		catch (Exception e) 
		{
			logger.info("CloseFutureListener.ChannelFuture.isDone()  : {} ", channelFuture.isDone() );
			logger.info("CloseFutureListener.ChannelFuture.isSuccess()  : {}",   channelFuture.isSuccess() );
			logger.info("CloseFutureListener.ChannelFuture.isCancellable()  : {}",  channelFuture.isCancellable() );
			logger.info("CloseFutureListener.ChannelFuture.isCancelled()  : {}", channelFuture.isCancelled() );
			logger.info("CloseFutureListener.channelFuture.channel().isActive()  : {}", channelFuture.channel().isActive());
//			logger.info("CloseFutureListener.channelFuture.channel().disconnect()  : {}", channelFuture.channel().disconnect());
			logger.info("CloseFutureListener.channelFuture.channel().isOpen()  : {}", channelFuture.channel().isOpen());
			logger.info("CloseFutureListener.channelFuture.channel().isRegistered()  : {}", channelFuture.channel().isRegistered());
			logger.info("CloseFutureListener.channelFuture.channel().isWritable()  : {}", channelFuture.channel().isWritable());
			
			e.printStackTrace();
			logger.info(LogHelper.getPrintStackTrace(e));

			channelFuture.channel().eventLoop().schedule(nettyClient, nettyClient.getReconnectTime() , TimeUnit.SECONDS);
			
		}
		finally 
		{
			
		}
		
		
	}
	

}

