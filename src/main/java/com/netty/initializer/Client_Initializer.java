package com.netty.initializer;

import java.util.Properties;

import com.handler.Client_read_Handler;
import com.handler.OPIdleStateHandler;
import com.network.netty.codec.ClientDecoder;
import com.network.netty.codec.ClientEncoder;
import com.util.ApplicationContextProvider;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

public class Client_Initializer extends ChannelInitializer<Channel> 
{

	@Override
	protected void initChannel(Channel channel) throws Exception 
	{
		ChannelPipeline pipeline = channel.pipeline();

		Properties contextProperties = (Properties)ApplicationContextProvider.getApplicationContext().getBean("contextProperties");
		
		int reader = Integer.valueOf(contextProperties.getProperty("connection.readeridletime"));
		int writer = Integer.valueOf(contextProperties.getProperty("connection.writeridletime"));
		int all = Integer.valueOf(contextProperties.getProperty("connection.allidletime"));
		
		pipeline.addLast("OPIdleStateHandler", new OPIdleStateHandler(reader, writer, all));
		
		pipeline.addLast("ClientDecoder", new ClientDecoder());
		pipeline.addLast("ClientEncoder", new ClientEncoder());
		
		pipeline.addLast("Client_read_Handler" , new Client_read_Handler());
		
	}
	
}
