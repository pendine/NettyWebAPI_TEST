package com.processor;

import io.netty.channel.ChannelHandlerContext;

public class EventMetaDataPacketProcess {

	private ChannelHandlerContext ctx;
	private String str;
	public EventMetaDataPacketProcess(ChannelHandlerContext ctx, String str)
	{
		this.ctx = ctx;
		this.str = str;
	}
	
}
