package com.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.domain.WebContentTmp;

import io.netty.channel.ChannelHandlerContext;

@Service("WebResponse")

public class WebResponse {
	
	private static final Logger logger = LoggerFactory.getLogger(WebResponse.class);
	private HashMap<ChannelHandlerContext , WebContentTmp> WebContentTmp;  //스케줄러에서 가져가서 pis의 정보를 업데이트할것임
	
	public WebResponse() {
		WebContentTmp= new HashMap<ChannelHandlerContext , WebContentTmp>();
	}
	
	
	public HashMap<ChannelHandlerContext, WebContentTmp> getWebContentTmpMap(){
		return this.WebContentTmp;
	}
	
	public WebContentTmp getWebContentTmpByCTX(ChannelHandlerContext ctx) {
		return WebContentTmp.get(ctx);
	}
	
	public void setWebContentTmp(ChannelHandlerContext ctx , WebContentTmp wt) {
		this.WebContentTmp.put(ctx, wt);
	}
	
	public void setWebContentTmpByCTX(ChannelHandlerContext ctx ) {
		this.WebContentTmp.put(ctx, new WebContentTmp() );
	}
	
	
}
