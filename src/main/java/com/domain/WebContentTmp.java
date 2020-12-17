package com.domain;

import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

@Data
public class WebContentTmp {
	
	ChannelHandlerContext ctx;
	int contentLength = 0;
	StringBuilder sb;
	
	Response response = new Response();
	
	public WebContentTmp() 
	{
		System.out.println("컨텐츠 정보 확인 용 객체 생성.");
		this.sb = new StringBuilder();
	}
	
	public WebContentTmp( ChannelHandlerContext ctx ) 
	{
		System.out.println("컨텐츠 정보 확인 용 객체 생성.");
		this.ctx = ctx;
		this.sb = new StringBuilder();
	}
	
	public ChannelHandlerContext getCtx() {
		return ctx;
	}
	public void setCtx(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}
	public int getContentLength() {
		return contentLength;
	}
	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}
	public StringBuilder getSb() {
		return sb;
	}
	public void setSb(StringBuilder sb) {
		this.sb = sb;
	}
	
	
	public String toString() {
		return " ChannelHandlerContext info : " + this.ctx.toString() 
			+" | content Length : " + this.contentLength
			+" | StringBuilder context : \n" + this.sb.toString();
	}
	

}
