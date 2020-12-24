package com.network.netty.codec;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequestDecoder;

public class ClientDecoder extends HttpRequestDecoder {

	private static final Logger logger = LoggerFactory.getLogger(ClientDecoder.class);

	@Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
		
		super.decode(ctx, buffer, out);
	}
	
}
