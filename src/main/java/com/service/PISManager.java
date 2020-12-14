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

import com.domain.PISTemplate;
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
public class PISManager implements InitializingBean{

	private static final Logger logger = LoggerFactory.getLogger(PISManager.class);
	//db에서 확인할 수 있는 제어기의 id로 변경.
	private HashMap<String, PISTemplate> pisTemplateMap;  //스케줄러에서 가져가서 pis의 정보를 업데이트할것임
		
	private ClientBootstrapFactory clientBootstrapFactory = (ClientBootstrapFactory) ApplicationContextProvider.getApplicationContext().getBean(ClientBootstrapFactory.class);
	private ExecutorServiceLauncher launcher = (ExecutorServiceLauncher) ApplicationContextProvider.getApplicationContext().getBean("launcher");
	private PISGroup pisGroup = (PISGroup) ApplicationContextProvider.getApplicationContext().getBean("pisGroup");
	
	@Override
	public void afterPropertiesSet() throws Exception {
//		pisTemplateMap = new HashMap<String, PISTemplate>();
		init();
	}

	public void init() {
		initMaster();
	}
	
	private void initMaster() {
		logger.info("-----PIS master info loading-----");
		
		String function = "getUltraSrtNcst";

		WebRequestTemplate webTmp = new WebRequestTemplate();
		webTmp.setDataType("JSON");
		webTmp.setDatetime("0000");//00시 00분
		webTmp.setDateDay("20201205");//2020년 12월 05일
		webTmp.setPageNo(1); // 목록중 첫번째
		webTmp.setNumOfRows(10); // N번째의 목록에 10개의 결과
		webTmp.setX("55"); // x좌표 설정
		webTmp.setY("127"); // y좌표 설정
		
		ClientHeader clientH = new ClientHeader(function, webTmp, "JSON", "get");
		
		ClientForm clientF = new ClientForm();
		clientF.setClientHeader(clientH);
		
		System.out.println(clientF.toString());

		HttpRequest tmpRequest = clientF.getClientHeader().getHttpRequest();

		System.out.println("uri : " +  tmpRequest.uri()  );
		System.out.println("protocolVersion : " +  tmpRequest.protocolVersion()  );
		
		System.out.println(" netty web client 생성");

		NettyWebClient nc = new NettyWebClient(webTmp.endPoint, 80, clientBootstrapFactory);

//		nc.connect(webTmp.endPoint, 80);
//		nc.connect();
		
		System.out.println(" netty web client connect 이후 출력문");
		try {
			nc.createRequest( webTmp.endPoint, 80, tmpRequest.uri() );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		logger.info("-----PIS master info loading complete-----");
	}
	
	public PISTemplate getPISTemplate(Channel ch) {
		for(Entry<String, PISTemplate> e : pisTemplateMap.entrySet()) {
			if(e.getValue().getCh() == null) continue;
			if(ch.equals(e.getValue().getCh())){
				PISTemplate t = pisTemplateMap.get(e.getKey());
				return t;
			}
		}
		return null;
	}


//	String TRMN_IP;
	public String getPISCtlrNmbrByIp(String ip) {		
		for(Entry<String, PISTemplate> e : pisTemplateMap.entrySet()) {
			if(e.getValue().getPRLT().getTRMN_IP().equals(ip) )
					return e.getValue().getPRLT().getCTLR_MNGM_NMBR();
			else continue;
		}
		return null;
	}

	public PISTemplate getPISTemplateByIp(String ip) {	
		for(Entry<String, PISTemplate> e : pisTemplateMap.entrySet()) {
			if(e.getValue().getPRLT().getTRMN_IP().equals(ip) )
				return e.getValue();
			else continue;
		}
		return null;
	}
	
	public PISTemplate getPISTemplateById(String id) {	
		for(Entry<String, PISTemplate> e : pisTemplateMap.entrySet()) {
//			if(e.getValue().getPRLT().getPRLT_CTLR_ID().equals(id) )
//				return e.getValue();
			if(e.getKey().equals(id) )
				return e.getValue();
			else continue;
		}
		return null;
	} 
	
	public PISTemplate getPISTemplateByPRLTID(String id) {	
		for(Entry<String, PISTemplate> e : pisTemplateMap.entrySet()) {
			if(e.getValue().getVoPRLT_ctlr().getPRLT_CTLR_ID().equals(id) )
				return e.getValue();
			else continue;
		}
		return null;
	} 
	
	public PISTemplate getPISTemplateByIpPort(String ip, String port) {	
		for(Entry<String, PISTemplate> e : pisTemplateMap.entrySet()) { 
			if(e.getValue().getPRLT().getTRMN_IP().equals(ip) && e.getValue().getPRLT().getTRMN_PORT().equals(port))
				return e.getValue();
			else continue;
		}
		return null;
	}
		
	public PISTemplate getPISTemplateByIpPort(String IP, int port) {
		String Port = String.valueOf(port);
		for(Entry<String, PISTemplate> e : pisTemplateMap.entrySet()) {
			if(e.getValue().getPRLT().getTRMN_IP().equals(IP) && e.getValue().getPRLT().getTRMN_PORT().equals(Port))
				return e.getValue();
			else continue;
		}
		return null;
	}
	
	public PISTemplate getPISTemplateByIdPort(String Id, int port) {
		String Port = Integer.toString(port);
		for(Entry<String, PISTemplate> e : pisTemplateMap.entrySet()) {
			if(e.getValue().getPRLT().getCTLR_MNGM_NMBR().equals(Id) && e.getValue().getPRLT().getTRMN_PORT().equals(Port))
				return e.getValue();
			else continue;
		}
		return null;
	}
	
	public HashMap<String, PISTemplate> getPISTemplateMap() {
		return pisTemplateMap;
	}

	public PISTemplate getPISTemplate(String PISCtlrId) {
		return pisTemplateMap.get(PISCtlrId);
	}
	
	
}
