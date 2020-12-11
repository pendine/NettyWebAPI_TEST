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
public class ClientProcessor implements Runnable{
	private static final Logger logger = LoggerFactory.getLogger(ClientProcessor.class);
	
	private ClientForm f;	
	private ChannelHandlerContext ctx;
	
	public ClientProcessor(ClientForm f, ChannelHandlerContext ctx) 
	{
		this.ctx = ctx;
		this.f = f;		
	}
	
	@Override
	public void run()  {
		try 
		{
			logger.info("PROCESSING... [{}]", ByteToHex.bytesToHex( this.f.getBodyBytes()) );
			
		} 
		catch (Exception e) 
		{
			logger.error(LogHelper.getPrintStackTrace(e));
			logger.error(">>> [{}]" , ByteToHex.bytesToHex( this.f.getBodyBytes()) );	
		}
	
	}
	
}
