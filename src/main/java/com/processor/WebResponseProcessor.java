package com.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.common.primitives.UnsignedBytes;
import com.protocol.client.ClientForm;
import com.util.ApplicationContextProvider;
import com.util.ByteToHex;
import com.util.LogHelper;

import io.netty.channel.ChannelHandlerContext;


@Service
@Scope("prototype")
public class WebResponseProcessor implements Runnable{
	
	private static final Logger logger = LoggerFactory.getLogger(WebResponseProcessor.class);
	
	private String context;
	private ChannelHandlerContext ctx;
	
	public WebResponseProcessor(String str, ChannelHandlerContext ctx) 
	{
		this.ctx = ctx;
		this.context = str;
	}
	
	@Override
	public void run()  {
		try 
		{
			logger.info("Test PROCESSING... [{}]", this.context );
			
			
			
		} 
		catch (Exception e) 
		{
			logger.error(LogHelper.getPrintStackTrace(e));
			logger.error(">>> [{}]" , this.context );	
		}
	
	}
	
}
