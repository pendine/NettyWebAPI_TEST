package com.processor;

import io.netty.channel.ChannelHandlerContext;

public class FrameMetaDataPacketProcess {

	private ChannelHandlerContext ctx;
	private String str;
	
	public FrameMetaDataPacketProcess(ChannelHandlerContext ctx, String str)
	{
		this.ctx = ctx;
		this.str = str;
	}
	
}
