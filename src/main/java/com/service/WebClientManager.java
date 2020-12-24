package com.service;

import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.WebRequestTemplate;
import com.launcher.ExecutorServiceLauncher;
import com.network.netty.NettyWebClient;
import com.network.netty.bootstrap.ClientBootstrapFactory;
import com.protocol.client.ClientForm;
import com.protocol.client.ClientHeader;
import com.util.ApplicationContextProvider;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.handler.codec.http.HttpRequest;

@Service("pisManager")
public class WebClientManager implements InitializingBean{

	private static final Logger logger = LoggerFactory.getLogger(WebClientManager.class);
		
	private ClientBootstrapFactory clientBootstrapFactory = (ClientBootstrapFactory) ApplicationContextProvider.getApplicationContext().getBean(ClientBootstrapFactory.class);
	private ExecutorServiceLauncher launcher = (ExecutorServiceLauncher) ApplicationContextProvider.getApplicationContext().getBean("launcher");
	
	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}

	public void init() {
		initMaster();
	}
	
	private void initMaster() {
		logger.info("-----Web Client Manager Start -----");
		
		String function = "getUltraSrtNcst";
		
		WebRequestTemplate webTmp = new WebRequestTemplate();
		webTmp.setDataType("JSON");
		webTmp.setDatetime("0000");		// 00시 00분
		webTmp.setDateDay("20201205");	// 2020년 12월 05일
		webTmp.setPageNo(1);			// 목록중 첫번째
		webTmp.setNumOfRows(10);		// N번째의 목록에 10개의 결과
		webTmp.setX("55");				// x좌표 설정
		webTmp.setY("127");				// y좌표 설정
		
		ClientHeader clientH = new ClientHeader(function, webTmp, "JSON", "get");
		
		ClientForm clientF = new ClientForm();
		clientF.setClientHeader(clientH);
		
		System.out.println(clientF.toString());

		HttpRequest tmpRequest = clientF.getClientHeader().getHttpRequest();

		System.out.println("uri : " +  tmpRequest.uri()  );
		System.out.println("protocolVersion : " +  tmpRequest.protocolVersion()  );
		
		System.out.println(" netty web client 생성");

		NettyWebClient nc = new NettyWebClient(webTmp.endPoint, 80, clientBootstrapFactory);

		System.out.println(" netty web client connect 이후 출력문");
		try {
			nc.createRequest( webTmp.endPoint, tmpRequest.uri() );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		logger.info("-----Web Client Manager End -----");
	}
	
}
