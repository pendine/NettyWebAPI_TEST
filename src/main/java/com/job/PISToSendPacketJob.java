package com.job;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteOrder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.domain.PISTemplate;
import com.enums.eOPOpcode;
import com.launcher.ExecutorServiceLauncher;
import com.service.PISManager;
import com.util.ApplicationContextProvider;
import com.util.ByteToHex;
import com.util.LogHelper;
import com.util.NettyHelper;
import com.network.netty.NettyTCPClient;
import com.protocol.client.ClientBody;
import com.protocol.client.ClientForm;
import com.protocol.client.ClientHeader;
import com.protocol.client.ClientTail;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;


public class PISToSendPacketJob extends QuartzJobBean {

	private static final Logger logger = LoggerFactory.getLogger(PISToSendPacketJob.class);

	public PISManager pisManager = (PISManager)ApplicationContextProvider.getApplicationContext().getBean("pisManager");

	public ExecutorServiceLauncher launcher = (ExecutorServiceLauncher)ApplicationContextProvider.getApplicationContext().getBean(ExecutorServiceLauncher.class);

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException 
	{
		
		short commandValue = 0;

		ClientForm clientForm = new ClientForm();
		for(Entry<String, PISTemplate> pis : pisManager.getPISTemplateMap().entrySet()) 
		{
			MDC.put("PIS", pis.getKey());
			logger.info("send to PIS the packet Job Start...");
			//상태 업데이트시 각 객체별로 갖고있는 채널에 패킷을 전송 
			try 
			{
				if(pis.getValue().getCh() != null) 
				{
					pis.getValue().getCh().writeAndFlush(clientForm);
				}
				else
				{
					logger.info("SEND MESSAGE JOB : TO PIS [ ID : {} | FAIL | Channel is null ]" , pis.getKey() );
				}
				
			}
			catch (Exception e) 
			{
				logger.info(" Exception PISTOSENDPACKET JOB : "+ LogHelper.getPrintStackTrace( e ) );	
			}
			
			logger.info("send to PIS the packet Job Done...");
			MDC.remove("PIS");
		}
		
	}
}
