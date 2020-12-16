package com.service;

import java.util.HashMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.domain.WebContentTmp;

import io.netty.channel.ChannelHandlerContext;


@Service("webMap")
public class WebMap implements InitializingBean{
	
	public static HashMap< ChannelHandlerContext , WebContentTmp > webContentMap;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("테스트용");
		init();
	}
	
	public void init() {
		masterLoading();
		System.out.println("마스터 로딩 이후");
//		webContentMap = new HashMap<ChannelHandlerContext, WebContentTmp>();
	}
	
	public void masterLoading() {
		System.out.println("마스터 로딩 호우");
	}
	
	public WebMap() {
		webContentMap = new HashMap<ChannelHandlerContext, WebContentTmp>();

	}
	
	public HashMap<ChannelHandlerContext, WebContentTmp> getWebContentTmpMap(){
		return this.webContentMap;
	}
	
	public WebContentTmp getWebContentTmpByCTX(ChannelHandlerContext ctx) {
		return webContentMap.get(ctx);
	}
	
	public void setWebContentTmp(ChannelHandlerContext ctx , WebContentTmp wt) {
		this.webContentMap.put(ctx, wt);
	}
	
	public void setWebContentTmpByCTX(ChannelHandlerContext ctx ) {
		System.out.println("CTX : " + ctx.toString());
		this.webContentMap.put(ctx, new WebContentTmp() );
	}


	

}
