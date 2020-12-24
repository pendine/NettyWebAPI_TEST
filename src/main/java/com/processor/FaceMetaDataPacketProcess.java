package com.processor;

import io.netty.channel.ChannelHandlerContext;

public class FaceMetaDataPacketProcess {

	private ChannelHandlerContext ctx;
	private String str;
	
	public FaceMetaDataPacketProcess(ChannelHandlerContext ctx, String str)
	{
		this.ctx = ctx;
		this.str = str;
	}
	
	
}
