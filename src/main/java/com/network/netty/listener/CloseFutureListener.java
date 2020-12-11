package com.network.netty.listener;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

import org.quartz.JobBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.network.netty.NettyTCPClient;
import com.service.PISManager;
import com.util.ApplicationContextProvider;
import com.util.LogHelper;
import com.util.NettyHelper;

public class CloseFutureListener  implements ChannelFutureListener// , ChannelInboundHandler
{
	
	private static final Logger logger = LoggerFactory.getLogger(CloseFutureListener.class);

	private PISManager pisManager = (PISManager) ApplicationContextProvider.getApplicationContext().getBean("pisManager");
	
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
			//		String pisID = pisManager.getPISTemplateByIp(ip).getPIS_ID();
			String pisID = pisManager.getPISTemplateByIpPort(ip, port).getPIS_ID();
			MDC.put("PIS", pisID );
			
			logger.info("재접속시도| PIS ID : {}" , pisID );
			logger.info("PIS ID : {} | CloseFutureListener.ChannelFuture.isDone()  : {} ", pisID ,channelFuture.isDone() );
			logger.info("PIS ID : {} | CloseFutureListener.ChannelFuture.isSuccess()  : {}", pisID , channelFuture.isSuccess() );
			logger.info("PIS ID : {} | CloseFutureListener.ChannelFuture.isCancellable()  : {}", pisID , channelFuture.isCancellable() );
			logger.info("PIS ID : {} | CloseFutureListener.ChannelFuture.isCancelled()  : {}", pisID , channelFuture.isCancelled() );
			logger.info("PIS ID : {} | CloseFutureListener.channelFuture.channel().isActive()  : {}", pisID , channelFuture.channel().isActive());
//			logger.info("PIS ID : {} | CloseFutureListener.channelFuture.channel().disconnect()  : {}", pisID , channelFuture.channel().disconnect());
			logger.info("PIS ID : {} | CloseFutureListener.channelFuture.channel().isOpen()  : {}", pisID , channelFuture.channel().isOpen());
			logger.info("PIS ID : {} | CloseFutureListener.channelFuture.channel().isRegistered()  : {}", pisID , channelFuture.channel().isRegistered());
			logger.info("PIS ID : {} | CloseFutureListener.channelFuture.channel().isWritable()  : {}", pisID , channelFuture.channel().isWritable());
			
			logger.info("Close Future | Success | Retry Connect | PIS ID : {} | Channel Close | {} "
					, pisID 
					, channelFuture.channel().toString()
					);
			
//			channelFuture.channel().flush();
//			channelFuture.channel().close().awaitUninterruptibly();
			
			channelFuture.channel().eventLoop().schedule(nettyClient, nettyClient.getReconnectTime() , TimeUnit.SECONDS);
			
		}
		catch (Exception e) 
		{
			String pisID = pisManager.getPISTemplateByIpPort(ip, port).getPIS_ID();
			
			MDC.put("PIS", pisID );
			
			logger.info("재접속시 예외사항 발생 | PIS ID : {}" , pisID );
			logger.info("PIS ID : {} | CloseFutureListener.ChannelFuture.isDone()  : {} ", pisID ,channelFuture.isDone() );
			logger.info("PIS ID : {} | CloseFutureListener.ChannelFuture.isSuccess()  : {}", pisID , channelFuture.isSuccess() );
			logger.info("PIS ID : {} | CloseFutureListener.ChannelFuture.isCancellable()  : {}", pisID , channelFuture.isCancellable() );
			logger.info("PIS ID : {} | CloseFutureListener.ChannelFuture.isCancelled()  : {}", pisID , channelFuture.isCancelled() );
			logger.info("PIS ID : {} | CloseFutureListener.channelFuture.channel().isActive()  : {}", pisID , channelFuture.channel().isActive());
//			logger.info("PIS ID : {} | CloseFutureListener.channelFuture.channel().disconnect()  : {}", pisID , channelFuture.channel().disconnect());
			logger.info("PIS ID : {} | CloseFutureListener.channelFuture.channel().isOpen()  : {}", pisID , channelFuture.channel().isOpen());
			logger.info("PIS ID : {} | CloseFutureListener.channelFuture.channel().isRegistered()  : {}", pisID , channelFuture.channel().isRegistered());
			logger.info("PIS ID : {} | CloseFutureListener.channelFuture.channel().isWritable()  : {}", pisID , channelFuture.channel().isWritable());
			
			e.printStackTrace();
			logger.info(LogHelper.getPrintStackTrace(e));

			channelFuture.channel().eventLoop().schedule(nettyClient, nettyClient.getReconnectTime() , TimeUnit.SECONDS);
			
		}
		finally 
		{
			MDC.remove("PIS");
			
		}
//		channelFuture.channel().eventLoop().schedule(nettyClient, nettyClient.getReconnectTime() , TimeUnit.SECONDS);
		
		
	}
	

}

