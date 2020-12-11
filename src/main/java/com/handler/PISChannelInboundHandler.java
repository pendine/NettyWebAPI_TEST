package com.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.processor.PISProcessor;
import com.protocol.pis.PISForm;
import com.service.PISManager;
import com.util.ApplicationContextProvider;
import com.util.ByteToHex;
import com.util.LogHelper;
import com.util.NettyHelper;
import com.util.TypeHelper;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class PISChannelInboundHandler extends SimpleChannelInboundHandler<Object> 
{

	private static final Logger logger = LoggerFactory.getLogger(PISChannelInboundHandler.class);
	
	private ThreadPoolTaskExecutor eventTaskExecutor = 
			(ThreadPoolTaskExecutor) ApplicationContextProvider.getApplicationContext().getBean("eventTaskExecutor");
	
	private PISManager pisManager = 
			(PISManager) ApplicationContextProvider.getApplicationContext().getBean(PISManager.class);
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object message) throws Exception 
	{
		String ip = NettyHelper.getRemoteAddress(ctx.channel());
		String pisID = pisManager.getPISCtlrNmbrByIp(ip);
		
		PISForm pisForm = (PISForm) message;
				
		this.eventTaskExecutor.submit(ApplicationContextProvider.getApplicationContext().getBean(PISProcessor.class, pisForm, ctx));
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception 
	{
		if( ctx.channel() != null) {
			String ip = NettyHelper.getRemoteAddress(ctx.channel());
			int port = NettyHelper.getRemotePort(ctx.channel());
			logger.info(" ip : {} | Cmmn_cd : {} | " 
					, ip
					, pisManager.getPISTemplateByIp( ip ).getVoPRLT_stts().getCMNC_STTS_CD() 
					);
			
			logger.error(" Remote[{}:{}] - {}", 
					ip , 
					port, 
					LogHelper.getPrintStackTrace(cause)
					);
		}
		super.exceptionCaught(ctx, cause);
		
	}
	
}
