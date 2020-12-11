package com.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.util.ApplicationContextProvider;
import com.util.DateHelper;
import com.util.LogHelper;
import com.vo.voUNIT_SYST;

public class PISServerStatusJob extends QuartzJobBean {

	private static final Logger logger = LoggerFactory.getLogger(PISServerStatusJob.class);

	voUNIT_SYST t ;
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException 
	{
		// TODO Auto-generated method stub
		logger.info("PIS Server STATUS Job Start...");
		t = new voUNIT_SYST();
		t.setSYST_ID("PIS01");
		t.setSYST_STTS_CD("SPS1");
		t.setCRTN_DT(DateHelper.getDate());

		try
		{
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			logger.info(LogHelper.getPrintStackTrace( e ) );
		}
		
		logger.info("PIS Server STATUS Job Done...");
	}

}
