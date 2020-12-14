package com.service;

import java.util.HashMap;

import com.domain.WebContentTmp;

import io.netty.channel.ChannelHandlerContext;


public class WebMap {
	
	public static HashMap< ChannelHandlerContext , WebContentTmp > webContentMap = new HashMap<ChannelHandlerContext, WebContentTmp>();
	
	public WebMap() {

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
