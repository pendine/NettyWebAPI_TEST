package com.job;

import java.nio.ByteOrder;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Transactional;

import com.domain.PISTemplate;
import com.enums.eOPOpcode;
import com.launcher.ExecutorServiceLauncher;
import com.network.netty.NettyTCPClient;
import com.protocol.client.ClientForm;
import com.service.PISManager;
import com.util.ApplicationContextProvider;
import com.util.DateHelper;
import com.util.LogHelper;
import com.vo.voPRLT_CTLR_STTS;

import io.netty.channel.Channel;


public class PISChannelStatusJob extends QuartzJobBean {
	//채널 연결 상태 정보를 DB에 저장함.

	private static final Logger logger = LoggerFactory.getLogger(PISChannelStatusJob.class);
	
	public PISManager pisManager = (PISManager)ApplicationContextProvider.getApplicationContext().getBean("pisManager");
	
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("PIS Channel Status Job Start...");
		
		for(Entry<String, PISTemplate> pis : pisManager.getPISTemplateMap().entrySet()) {
			MDC.put("PIS", pis.getKey());
			try {
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.info(LogHelper.getPrintStackTrace( e ) );
			}
			MDC.remove("PIS");
		}
		
		logger.info("PIS Channel Status Job Done...");
	}
}
