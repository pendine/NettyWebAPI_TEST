package com.network.netty.codec;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.json.JsonObjectDecoder;

public class JsonDecoder extends JsonObjectDecoder{

	
	  @Override
	  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		  
		  System.out.println("JsonDecoder | ctx : " + ctx.toString() + " :| \n channel config" + ctx.channel().config());
		  
		  
		  System.out.println("before super");
		  super.decode(ctx, in, out);
		  System.out.println("after super");
		  
	  }
	  
}
