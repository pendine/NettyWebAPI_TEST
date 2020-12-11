package com.netty.initializer;

import java.util.Properties;

import com.handler.PISChannelInboundHandler;
import com.network.netty.codec.ClientDecoder;
import com.network.netty.codec.ClientEncoder;
import com.util.ApplicationContextProvider;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

public class NettyClientChannelInittializer extends ChannelInitializer<Channel> {

	@Override
	protected void initChannel(Channel channel) throws Exception {
		ChannelPipeline pipeline = channel.pipeline();

		Properties contextProperties = (Properties)ApplicationContextProvider.getApplicationContext().getBean("contextProperties");
		
		int reader = Integer.valueOf(contextProperties.getProperty("connection.readeridletime"));
		int writer = Integer.valueOf(contextProperties.getProperty("connection.writeridletime"));
		int all = Integer.valueOf(contextProperties.getProperty("connection.allidletime"));
		
		pipeline.addLast();
		pipeline.addLast();
		pipeline.addLast();
		pipeline.addLast();
	}
}