package com.handler;

import java.nio.ByteOrder;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.processor.ClientProcessor;
import com.protocol.client.ClientForm;
import com.protocol.op.OPForm;
import com.util.ApplicationContextProvider;
import com.util.ByteToHex;
import com.util.LogHelper;
import com.util.NettyHelper;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class OPChannelInboundHandler extends SimpleChannelInboundHandler<Object> 
{

	private static final Logger logger = LoggerFactory.getLogger(OPChannelInboundHandler.class);
	
	private ThreadPoolTaskExecutor eventTaskExecutor = 
			(ThreadPoolTaskExecutor) ApplicationContextProvider.getApplicationContext().getBean("eventTaskExecutor");
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object message) throws Exception 
	{
		ClientForm f = (ClientForm) message;

		this.eventTaskExecutor.submit(ApplicationContextProvider.getApplicationContext().getBean(ClientProcessor.class, f, ctx));
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception 
	{
		logger.error("Remote[{}:{}] - {}", NettyHelper.getRemoteAddress(ctx.channel()), NettyHelper.getRemotePort(ctx.channel()), 
				LogHelper.getPrintStackTrace(cause));
		
		super.exceptionCaught(ctx, cause);
	}
	
	
}
