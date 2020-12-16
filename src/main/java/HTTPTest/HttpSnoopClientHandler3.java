package HTTPTest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.domain.WebContentTmp;
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

public class HttpSnoopClientHandler3 extends SimpleChannelInboundHandler<Object> {
	
	static int contentLength = 0;
	
//	WebResponse webResponseMap = (WebResponse) ApplicationContextProvider.getApplicationContext().getBean(WebResponse.class);
//	WebMap webMap = new WebMap();
//	WebMap webMap = (WebMap) ApplicationContextProvider.getApplicationContext().getBean(WebMap.class);
	
//	WebContentTmp tmp = null;
	
    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) {

    	System.out.println(" HttpSnoopClientHandler3 | channelRead 동작 =====================================================");
    	
    	ByteBuf bf;
    	
        if (msg instanceof ByteBuf) {
        	System.out.println("ByteBuf 로 변환 가능.");	
        	bf = (ByteBuf)msg;
        	int tmp_length = bf.readableBytes();
        	
        	byte[] tmp = new byte[tmp_length];

        	bf.readBytes(tmp);
        	
        	System.out.println(" channel Read  tmp(Hex) : " + ByteToHex.bytesToHex(tmp));
        	
        }   
        
        if(msg instanceof String) {
        	System.out.println("String 으로 변환 가능.");	

        	String aaa = (String) msg;
        	System.out.println(" msg String : " + msg);
        	
        }
        
        if(msg != null) {
        	System.out.println("msg 널값이 아님. ");
        }else {
        	System.out.println("msg 널값 임. ");
        }
        
//        try {
//			super.channelRead(ctx, msg);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
        System.out.println(" HttpSnoopClientHandler3 | channelRead 동작끝 =====================================================");
    }

    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//    	if(!webResponseMap.getWebContentTmpMap().containsKey(ctx)) {
//    		webResponseMap.setWebContentTmpByCTX(ctx);
//    		System.out.println("키값으로 찾을수 있는 요소가 없기 때문에 새로 생성");
//    	}

		System.out.println("CTX : " + ctx.toString());
		
//    	webMap.setWebContentTmpByCTX(ctx);
    	
//        ctx.fireChannelActive();
		System.out.println("channelActive Super before");
		super.channelActive(ctx);
		System.out.println("channelActive Super after");
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}