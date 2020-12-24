package com.processor;

import io.netty.channel.ChannelHandlerContext;

public class CameraMetaDataPacketProcess 
{
	private ChannelHandlerContext ctx;
	private String str;
	
	CameraMetaDataPacketProcess(){}
	
	CameraMetaDataPacketProcess(ChannelHandlerContext ctx, String str)
	{
		this.ctx = ctx;
		this.str = str;
	}

}
