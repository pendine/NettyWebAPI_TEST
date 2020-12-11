package com.network.netty.codec;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.domain.PISTemplate;
import com.protocol.client.ClientForm;
import com.service.PISManager;
import com.util.ApplicationContextProvider;
import com.util.ByteToHex;
import com.util.NettyHelper;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpObjectEncoder;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpVersion;

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
