package com.processor;

import io.netty.channel.ChannelHandlerContext;

public interface iProcessor<T> {

//	public ApplicationContext context = ApplicationContextProvider.getApplicationContext();	
	
	public void processing(T message, ChannelHandlerContext ctx);

}
