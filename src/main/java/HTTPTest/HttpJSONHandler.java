package HTTPTest;

import java.util.List;

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
import io.netty.handler.codec.json.JsonObjectDecoder;

public class HttpJSONHandler extends JsonObjectDecoder {
	
	static int contentLength = 0;
	
//	WebResponse webResponseMap = (WebResponse) ApplicationContextProvider.getApplicationContext().getBean(WebResponse.class);
//	WebMap webMap = new WebMap();
//	WebMap webMap = (WebMap) ApplicationContextProvider.getApplicationContext().getBean(WebMap.class);
	
	WebContentTmp tmp = null;

	@Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

    	System.out.println("HttpJSONHandler | decode 동작 =====================================================");
    	
    	int length = in.readableBytes();
    	byte[] byteArr = new byte[length];
    	
    	in.readBytes(byteArr);
    	
    	System.out.println("length : " + length );
    	System.out.println("byteArr(Hex) : " + ByteToHex.bytesToHex(byteArr));
    	
    	
       
//    	out.add(e);
    	
    	System.out.println("before superMethod ");
    	super.decode(ctx, in, out);
    	System.out.println("after superMethod ");
    	
        System.out.println("channelRead 동작끝 =====================================================");
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