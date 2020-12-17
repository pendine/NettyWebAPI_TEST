package com.network.netty.codec;

import java.util.List;

import com.domain.WebContentTmp;
import com.service.WebMap;
import com.service.WebResponse;
import com.util.ApplicationContextProvider;
import com.util.ByteToHex;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CodecException;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpContentDecoder;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.CharsetUtil;

public class WebDecoder2 extends ByteToMessageDecoder{

	private final boolean strict;

	private StringBuilder sb;
	
//	WebResponse webResponseMap = (WebResponse) ApplicationContextProvider.getApplicationContext().getBean("WebResponse");
		
//	WebMap webMap = (WebMap) ApplicationContextProvider.getApplicationContext().getBean(WebMap.class);

    public WebDecoder2() {
        this(false);
    }

    public WebDecoder2(boolean strict) {
        this.strict = strict;
    }
	
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
    	System.out.println("WebDecoder2 | 디코딩할때 여기탐 decode ");
    	
    	System.out.println("length : "+msg.readableBytes());
    	
    	byte[] byteArr = new byte[msg.readableBytes()];
    	msg.readBytes(byteArr);
    	String result =  ByteToHex.bytesToHex(byteArr);
    	System.out.println("byteArr (Hex) : " + result );
    	
    	
    	out.add(result);
    	    	
    }
    
    
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("WebDecoder2 |  channelActive");
		// TODO Auto-generated method stub
		super.channelActive(ctx);
	}

}
