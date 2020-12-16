package HTTPTest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.domain.WebContentTmp;
import com.service.WebMap;
import com.util.ApplicationContextProvider;

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
import io.netty.handler.codec.http.HttpObject;

public class HttpSnoopClientHandler2 extends SimpleChannelInboundHandler<HttpObject> {
	
	static int contentLength = 0;
	
//	WebResponse webResponseMap = (WebResponse) ApplicationContextProvider.getApplicationContext().getBean(WebResponse.class);
//	WebMap webMap = new WebMap();
//	WebMap webMap = (WebMap) ApplicationContextProvider.getApplicationContext().getBean(WebMap.class);
	
//	WebContentTmp tmp = null;
	
    @Override
    public void channelRead0(ChannelHandlerContext ctx, HttpObject msg) {

//    	System.out.println("channelRead 동작 =====================================================");
//    	
//        if (msg instanceof WebContentTmp) {
//        	if(this.tmp == null)
//        	{
//        		System.out.println("맨처음임 비어있음.");
//        		tmp = (WebContentTmp) msg;
//        	}
////        	else if(tmp.getCtx() == msg.getCtx())
//        	else if( webMap.getWebContentTmpByCTX(ctx) != null )
//        	{
//        		tmp = webMap.getWebContentTmpByCTX(ctx);
//        		if(tmp.getSb().toString().length() < tmp.getContentLength() )
//	        	{
//        			
////	        		System.out.println("받았지만 아직 지정된 길이보다 짧음. msg.getSb().toString() : " + msg.getSb().toString() );
////        			System.out.println("받았지만 아직 지정된 길이보다 짧음. 저장함.");
////	        		tmp.getSb().append(msg.getSb().toString());
//        			
//        			System.out.println("받았지만 아직 지정된 길이보다 짧음. 저장하지 않고 반환.");
//        			return;
//	        	}
//	        	else if(tmp.getSb().toString().length() >= tmp.getContentLength() )
//	        	{
//	        		System.out.println("받았음. 지정된 길이보다 길거나 같음. msg.getSb().toString() : ");
//	        		System.out.println("tmp . length : " + tmp.getContentLength() );
//	        		System.out.println("tmp . ctx	 : " + tmp.getCtx()	);
//	        		System.out.println( msg.getSb().toString() );
//	        		
//	        		
//	        		JSONParser parser = new JSONParser();
//					try {
//						Object obj;
//						obj = parser.parse( msg.getSb().toString() );
//						JSONObject jsonObj = (JSONObject) obj;
//						
//						
//						System.out.println(" jsonObj.toString()     : " + jsonObj.toString() );
//						System.out.println(" jsonObj.toJSONString() : " + jsonObj.toJSONString() );
//						
//						
//						System.out.println("jsonObj.get(\"response\") : " + jsonObj.get("response"));
//						System.out.println("jsonObj.get(\"header\") : " + jsonObj.get("header"));
//						System.out.println("jsonObj.get(\"resultCode\") : " + jsonObj.get("resultCode"));
////						System.out.println("jsonObj.get("body") : " + jsonObj.get("body"));
//						
//					} catch (ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
////	        		JSONArray jsonObject = new JSONArray( msg.getSb().toString() );
//	        	}
//        	}
//        	
////        	System.out.println(" Reader method : webcontentTmp | ctx.toString : " + tmp.getCtx().toString() + "| ctx : " +tmp.getCtx());
////        	System.out.println(" Reader method : webcontentTmp | contentLength :" + tmp.getContentLength() );
////        	System.out.println(" Reader method : webcontentTmp | sb : " + tmp.getSb().toString() );
//        	
//        }
//        	
//        
//        
//        System.out.println("channelRead 동작끝 =====================================================");
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