package com.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;



public class PISChannelStatusJob extends QuartzJobBean {
	//채널 연결 상태 정보를 DB에 저장함.

	private static final Logger logger = LoggerFactory.getLogger(PISChannelStatusJob.class);
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
//		for(Entry<String, PISTemplate> pis : pisManager.getPISTemplateMap().entrySet()) {
//			MDC.put("WEB", pis.getKey());
//			try {
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				logger.info(LogHelper.getPrintStackTrace( e ) );
//			}
//			MDC.remove("WEB");
//		}
		
	}
}
