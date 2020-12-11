package com.network.netty.listener;

import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.domain.PISTemplate;
import com.network.netty.NettyTCPClient;
import com.network.netty.bootstrap.ClientBootstrapFactory;
import com.service.PISManager;
import com.util.ApplicationContextProvider;
import com.util.LogHelper;
import com.util.NettyHelper;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

public class ConnectFutureListener implements ChannelFutureListener {

	private static final Logger logger = LoggerFactory.getLogger(ConnectFutureListener.class);
	private PISManager pisManager = (PISManager) ApplicationContextProvider.getApplicationContext().getBean("pisManager");
	private ClientBootstrapFactory clientBootstrapFactory = (ClientBootstrapFactory) ApplicationContextProvider.getApplicationContext().getBean("PIS_ClientBootstrapFactory");
	
	//private final NettyTCPClient nettyClient;
	private NettyTCPClient nettyClient; // 2019.04.24 dsnoh
//	private String pisId;
	
	public ConnectFutureListener(NettyTCPClient nettyClient) {
		this.nettyClient = nettyClient;
//		this.pisId = pisManager.getPISTemplateByIpPort(nettyClient.getHost(), nettyClient.getPort()).getPRLT().getCTLR_MNGM_NMBR();
	}
	
	@Override
	public void operationComplete(ChannelFuture channelFuture) throws Exception {
	
		try {
			String ip = NettyHelper.getRemoteAddress(channelFuture.channel());
			PISTemplate templte = pisManager.getPISTemplateByIp( ip );
			MDC.put("PIS", templte.getPIS_ID() );
			
			if (!channelFuture.isSuccess()) 
			{
				if( LogHelper.getPrintStackTrace(channelFuture.cause()).indexOf("AnnotatedConnectException") > 0) 
				{
					logger.warn("Connect Fail | Connection Future | Retry Connect - {} | PIS : {}",channelFuture.channel().toString(), " Server connection information has been lost " );
				}
				else if( LogHelper.getPrintStackTrace(channelFuture.cause()).indexOf("ConnectTimeoutException") > 0) 
				{
					logger.warn("Connect Fail | Connection Future | Retry Connect - {} | {}",channelFuture.channel().toString(), "Target Server Connect is Timeout"  );
				}
				else 
				{
					logger.warn("Connect Fail | Connection Future | Retry Connect - {} | {}",channelFuture.channel().toString(), LogHelper.getPrintStackTrace(channelFuture.cause()));
				}
				channelFuture.channel().eventLoop().schedule(nettyClient, nettyClient.getReconnectTime(), TimeUnit.SECONDS);
			}	
			else
			{
				logger.warn("ConnectFutureListener | operationComplete | channelFuture.isSuccess else ");
			}
			if (channelFuture.isSuccess()) {
				templte.setCh(channelFuture.channel() );
			}
			MDC.remove("PIS");
		}
		catch(NullPointerException e) 
		{
			logger.info(" To Connect for Channel exception | Channel is null");
			logger.error( LogHelper.getPrintStackTrace(e) );
//			channelFuture.channel().eventLoop().schedule(nettyClient,  nettyClient.getReconnectTime(), TimeUnit.SECONDS);
		}
		
	}
	
	
	
	
}
