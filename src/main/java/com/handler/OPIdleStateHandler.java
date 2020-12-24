package com.handler;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.util.ApplicationContextProvider;
import com.util.LogHelper;
import com.util.NettyHelper;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;


public class OPIdleStateHandler extends IdleStateHandler {

	private static final Logger logger = LoggerFactory.getLogger(OPIdleStateHandler.class);
		
	Properties contextProperties = (Properties)ApplicationContextProvider.getApplicationContext().getBean("contextProperties");	
	
	public OPIdleStateHandler(int readerIdleTimeSeconds,int writerIdleTimeSeconds, int allIdleTimeSeconds) 
	{
		super(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception 
	{
		
		if (evt.state() == IdleState.ALL_IDLE )
		{	
			
		}
		else if(evt.state() == IdleState.READER_IDLE) 
		{
			
		}
		else if(evt.state() == IdleState.WRITER_IDLE) 
		{
			
		}
		
		
	}

	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception 
	{
		// TODO Auto-generated method stub
		
		logger.info("Operation terminal : Connected | Channel : {}", ctx.channel());
		
		super.channelActive(ctx);
		
	}

	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//		logger.info("Channel Disconnect, #Remote[{}:{}]", NettyHelper.getRemoteAddress(ctx.channel()), NettyHelper.getRemotePort(ctx.channel()));	
		logger.info("Operation terminal : DisConnected | Channel : {}", ctx.channel());

		super.channelInactive(ctx); 
		
		try 
		{
			
		}
		catch (Exception e) 
		{
			logger.error(LogHelper.getPrintStackTrace(e));
		}
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception 
	{
		logger.error("Channel Exception. #e:{}, #Remote[{}:{}] ", 
				LogHelper.getPrintStackTrace(cause),
				NettyHelper.getRemoteAddress(ctx.channel()), NettyHelper.getRemotePort(ctx.channel()));			
		
		OPIdleStateHandler.disconnectOnFlushChannel(ctx.channel());
		super.exceptionCaught(ctx, cause);
	}
	
	@Override
	public void close(ChannelHandlerContext ctx, ChannelPromise future) throws Exception 
	{
		logger.info("UserIdleStateHandler : ChannelClosed - Channel #{} : {}", ctx.channel().hashCode() ,ctx.channel().remoteAddress());			
		super.close(ctx, future);
	}
	
	public static void disconnectOnFlushChannel(Channel ch) 
	{
		if(!ch.isActive()) return;
		
		logger.info("[DISCONNECT-On-Flush] Start to disconnect channel #hascode:{} #ip: {}", ch.hashCode(),ch.remoteAddress().toString()); 
		ch.flush();
    	ChannelFuture f = ch.disconnect().awaitUninterruptibly();
    	
    	if(f.isDone())
    	{
    		logger.info("[DISCONNECT-On-Flush] Done channel #hascode:{} #ip: {}",ch.hashCode(), ch.remoteAddress().toString()); 
    	}
    	else
    	{
    		logger.info("[DISCONNECT-On-Flush] Fail 1 channel #hascode:{} #ip: {}",ch.hashCode(), ch.remoteAddress().toString()); 
    	}
    	if(f.isSuccess())
    	{
    		logger.info("[DISCONNECT-On-Flush] Success channel #hascode:{} #ip: {}",ch.hashCode(), ch.remoteAddress().toString()); 
    	}
    	else
    	{
    		logger.info("[DISCONNECT-On-Flush] Fail 2 channel #hascode:{} #ip: {}",ch.hashCode(), ch.remoteAddress().toString());
    	}
    	
//    	f.channel().close();
    	
	}	
	
}
