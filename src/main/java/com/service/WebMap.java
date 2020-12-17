package com.service;

import java.util.HashMap;
import java.util.Map.Entry;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.domain.WebContentTmp;
import com.util.NettyHelper;

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
	
	public static HashMap<ChannelHandlerContext, WebContentTmp> getWebContentTmpMap(){
		return webContentMap;
	}
	
	/**
	 * web content 를 문자열로 저장할 객체 맵퍼에서,
	 * 채널 핸들러 컨텍스트의 채널에서 아이피로 구분하여, 원격지 주소에 해당하는 아이피 를 갖고있는 객체 정보를 반환함.
	 * */
	public static WebContentTmp getWebContentTmpByCTX(ChannelHandlerContext ctx) {
		for(Entry<ChannelHandlerContext, WebContentTmp> maps : webContentMap.entrySet() ) {
			if( NettyHelper.getRemoteAddress( maps.getKey().channel() ).equals( NettyHelper.getRemoteAddress(ctx.channel()) ) )
			{
				return maps.getValue();
			}
		}
		return null;
	}
	
	public static void setWebContentTmp(ChannelHandlerContext ctx , WebContentTmp wt) {
		webContentMap.put(ctx, wt);
	}
	
	public static void setWebContentTmpByCTX(ChannelHandlerContext ctx ) {
		System.out.println("CTX : " + ctx.toString());
		webContentMap.put(ctx, new WebContentTmp() );
	}


	

}
