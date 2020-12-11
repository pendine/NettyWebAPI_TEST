package com.processor;

import java.nio.ByteOrder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.domain.PISTemplate;
import com.google.common.primitives.UnsignedBytes;
import com.util.ApplicationContextProvider;
import com.util.ByteToHex;
import com.util.LogHelper;
import com.util.NettyHelper;
import com.protocol.client.ClientForm;
import com.protocol.pis.PISForm;
import com.service.PISManager;

import io.netty.channel.ChannelHandlerContext;


@Service
@Scope("prototype")
public class PISProcessor implements Runnable{
	private static final Logger logger = LoggerFactory.getLogger(PISProcessor.class);
	
	private PISForm f;	
	private ChannelHandlerContext ctx;
	public PISManager PISManager = (PISManager)ApplicationContextProvider.getApplicationContext().getBean("pisManager");
	
	public PISProcessor(PISForm f, ChannelHandlerContext ctx) {
		this.ctx = ctx;
		this.f = f;		
	}
	
	@Override
	public void run()  {
		try {
			String ip =  NettyHelper.getRemoteAddress(ctx.channel());
			
			String pidID = PISManager.getPISTemplateByIp(ip).getPIS_ID();
			
			int port = NettyHelper.getRemotePort(ctx.channel());
			
			MDC.put("PIS", pidID);

			PISTemplate pisTem = PISManager.getPISTemplate(pidID);
			logger.info( "PROCESSING... [{}] :: PIS ID : {}  PIS NAME : {}  CTX = {}"
					, ByteToHex.bytesToHex( this.f.array(ByteOrder.BIG_ENDIAN)) 
					, pisTem.getVoPRLT_ctlr().getCTLR_MNGM_NMBR()
					, pisTem.getVoPRLT_ctlr().getCTLR_MNGM_NM() 
					, ctx
					);
			
			MDC.remove("PIS");
			
		} catch (Exception e) {
			logger.error(LogHelper.getPrintStackTrace(e));
			logger.error(">>> [{}]" , ByteToHex.bytesToHex( this.f.array(ByteOrder.BIG_ENDIAN) ) );	
		}
	
	}
	
}
