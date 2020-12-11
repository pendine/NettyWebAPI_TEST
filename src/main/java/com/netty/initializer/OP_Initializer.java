package com.netty.initializer;

import java.util.Properties;

import com.handler.OPChannelInboundHandler;
import com.handler.OPIdleStateHandler;
import com.handler.PISChannelInboundHandler;
import com.handler.PISIdleStateHandler;
import com.network.netty.codec.OPDecoder;
import com.network.netty.codec.OPEncoder;
import com.network.netty.codec.PISTCPDecoder;
import com.network.netty.codec.PISTCPEncoder;
import com.util.ApplicationContextProvider;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

public class OP_Initializer extends ChannelInitializer<Channel> {

	@Override
	protected void initChannel(Channel channel) throws Exception {
		ChannelPipeline pipeline = channel.pipeline();

		Properties contextProperties = (Properties)ApplicationContextProvider.getApplicationContext().getBean("contextProperties");
		
		int reader = Integer.valueOf(contextProperties.getProperty("connection.readeridletime"));
		int writer = Integer.valueOf(contextProperties.getProperty("connection.writeridletime"));
		int all = Integer.valueOf(contextProperties.getProperty("connection.allidletime"));
		
		pipeline.addLast("OPIdleStateHandler", new OPIdleStateHandler(reader, writer, all));
		
		pipeline.addLast("OPDecoder", new OPDecoder());
		pipeline.addLast("OPEncoder", new OPEncoder());
		
		pipeline.addLast("OPChannelInboundHandler" , new OPChannelInboundHandler());
		
	}
	
}
