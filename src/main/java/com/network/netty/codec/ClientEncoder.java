package com.network.netty.codec;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.protocol.client.ClientForm;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestEncoder;

public class ClientEncoder extends HttpRequestEncoder {

	private static final Logger logger = LoggerFactory.getLogger(ClientEncoder.class);

	  @Override
	  protected void encodeInitialLine(ByteBuf buf, HttpRequest request) throws Exception {
		  super.encodeInitialLine(buf, request);
	  }
	  
	  @Override
	  protected void encode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {
		  ClientForm cf = (ClientForm) msg;
		  
		  out.add(cf);
		  ctx.writeAndFlush(out);
		  
	  }
	
	
}
