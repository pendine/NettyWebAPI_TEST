package com.processor;

import io.netty.channel.ChannelHandlerContext;

public class PTZMetaDataPacketProcess 
{

	private ChannelHandlerContext ctx;
	private String str;
	
	public PTZMetaDataPacketProcess(ChannelHandlerContext ctx, String str)
	{
		this.ctx = ctx;
		this.str = str;
	}
}
