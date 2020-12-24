package HTTPTest;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.domain.WebContentTmp;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.service.WebMap;
import com.util.ApplicationContextProvider;
import com.util.ByteToHex;

import io.netty.buffer.ByteBuf;

/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultLastHttpContent;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpObject;

public class HttpSnoopClientHandler2 extends SimpleChannelInboundHandler<Object> {
	
	static int contentLength = 0;
	
//	WebResponse webResponseMap = (WebResponse) ApplicationContextProvider.getApplicationContext().getBean(WebResponse.class);
//	WebMap webMap = new WebMap();
//	WebContentTmp tmp = null;
	
    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws ParseException {
    	if(msg instanceof JSONObject) 
    	{
    		System.out.println("JSONObject");
    		String result = ((JSONObject) msg).toJSONString();
    		System.out.println("result : " + result );
    	}
    	if(msg instanceof ByteBuf) 
    	{
    		System.out.println("ByteBuf");
    		int readable = ((ByteBuf) msg).readableBytes();
    		byte[] tmp = new byte[readable];
    		
    		((ByteBuf) msg).readBytes(tmp);
    		
    		System.out.println("result : " + ByteToHex.bytesToHex(tmp) );
    	}
    	if(msg instanceof JSONArray) 
    	{
    		System.out.println("JSONArray");
    		String result = ((JSONArray) msg).toJSONString();
    		System.out.println("result : " + result );
    	}
    	if(msg instanceof String) 
    	{
    		System.out.println("String");
    		String result = ((String) msg);
    		System.out.println("result : " + result );
    	}
    	if(msg instanceof DefaultLastHttpContent) 
    	{
    		System.out.println("DefaultLastHttpContent");
    		ByteBuf byteBuf = ((DefaultLastHttpContent) msg).content();
    		int length = byteBuf.readableBytes();
    		
    		byte[] tmp = new byte[length];

    		String parseTarget = ByteToHex.bytesToString(tmp);
    		
    		byteBuf.getBytes(0,tmp);
    		
    		System.out.println("result : " + ByteToHex.bytesToHex(tmp) );
    		System.out.println("result : " + ByteToHex.bytesToString(tmp) );
    		
    		JSONParser jsonParse = new JSONParser();
    	    JSONObject obj =  (JSONObject)jsonParse.parse(parseTarget);
    	   
    		System.out.println("obj : " + obj);
    		
    		System.out.println("header : " + obj.get("header"));
    		
    		
    	}
    	
    	/*
    	 * if(msg instanceof HttpContent) 
    	 
    	{
    		System.out.println("HttpContent");
    		ByteBuf byteBuf = ((DefaultLastHttpContent) msg).content();
    		int length = byteBuf.readableBytes();
    		
    		byte[] tmp = new byte[length];
    		
    		byteBuf.getBytes(0,tmp);
    		
    		String parseTarget = ByteToHex.bytesToString(tmp);
    		
    		System.out.println("result : " + ByteToHex.bytesToHex(tmp) );
    		System.out.println("result : " + parseTarget );
    		
    		
    		JSONParser simpleJsonParsor = new JSONParser();
    		JsonParser gsonJsonParsor = new JsonParser();
    		
    		JSONParser jsonParse = new JSONParser();
    	    JSONObject obj =  (JSONObject)jsonParse.parse(parseTarget);
    	   
    		System.out.println("obj : " + obj);
    		
    		System.out.println("header : " + obj.get("header"));
    		
    	}
    	*/
    	else
    	{
    		System.out.println( msg.getClass() );
    		System.out.println("예상한 결과가 아님.");
    		System.out.println( msg.toString() );
    	}
    }

    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//    	if(!webResponseMap.getWebContentTmpMap().containsKey(ctx)) {
//    		webResponseMap.setWebContentTmpByCTX(ctx);
//    		System.out.println("키값으로 찾을수 있는 요소가 없기 때문에 새로 생성");
//    	}

		System.out.println("CTX : " + ctx.toString());
		
//    	webMap.setWebContentTmpByCTX(ctx);
    	
        ctx.fireChannelActive();
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}