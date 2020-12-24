package HTTPTest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.domain.Response;
import com.domain.Separate_part;
import com.domain.WebContentTmp;
import com.enums.eCategory;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.processor.ClientProcessor;
import com.processor.WebResponseProcessor;
import com.protocol.weather.Item;
import com.protocol.weather.weatherAPIForm;
import com.service.WebMap;
import com.util.ApplicationContextProvider;
import com.util.NettyHelper;

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
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpObject;

public class HttpSnoopClientHandler extends SimpleChannelInboundHandler<HttpObject> {
	static int contentLength = 0;
//	WebResponse webResponseMap = (WebResponse) ApplicationContextProvider.getApplicationContext().getBean(WebResponse.class);
//	WebMap webMap = new WebMap();
//	WebMap webMap = (WebMap) ApplicationContextProvider.getApplicationContext().getBean(WebMap.class);
//	WebMap webMap = (WebMap) ApplicationContextProvider.getApplicationContext().getBean("webMap");
	
//	private ThreadPoolTaskExecutor eventTaskExecutor = 
//			(ThreadPoolTaskExecutor) ApplicationContextProvider.getApplicationContext().getBean("eventTaskExecutor");
	
	
    @Override
    public void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws ParseException {
    	
//    	StringBuilder sb = new StringBuilder();
    	System.out.println("HttpSnoopClientHandler | channelRead 동작 =====================================================");
    	System.out.println("HttpSnoopClientHandler | channelRead0 | 채널값 확인용 ");
    	System.out.println("ctx : "+ctx.toString() + " ip : "+ NettyHelper.getRemoteAddress( ctx.channel() ) );
    	System.out.println("ctx.channel id : " + ctx.channel().id());
    	System.out.println("ctx.name : " + ctx.name());
    	
    	WebContentTmp contentInfo = null;
    	
		if( WebMap.getWebContentTmpByCTX(ctx) == null) 
    	{
    		System.out.println(ctx.toString());
    		System.out.println("해당 원격지 아이피의 객체정보 없음 리턴 ");
    		System.out.println("호스트 확인필요 : " + NettyHelper.getRemoteAddress(ctx.channel()));
    		return;
    	}
    	else
    	{
    		System.out.println(ctx.toString());
    		System.out.println("해당 원격지 아이피의 객체정보 존재함.");
    		contentInfo =  WebMap.getWebContentTmpByCTX(ctx);
    		System.out.println("호스트 확인 : " + NettyHelper.getRemoteAddress(ctx.channel()));
    	}
    	
    	int strLen = contentInfo.getContentLength();
    	String info = contentInfo.getSb().toString();

    	try {
//    		this.eventTaskExecutor.submit(ApplicationContextProvider.getApplicationContext().getBean(WebResponseProcessor.class, info, ctx));
    		WebResponseProcessor wrp = new WebResponseProcessor(info, ctx);
    		wrp.run();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    	/*
    	try 
    	{
    		System.out.println("info : "+ info);
//    		System.out.println("json.toJSONString() : " + json.toJSONString());
//    		System.out.println("json.toString()     : " + json.toString());
//    		Gson gson = new Gson();
//    		Response response = gson.fromJson(info, Response.class);
    		
    		Separate_part sp = new Separate_part();
    		String[] tmp = sp.separate(info);
    		
    		for(String str : tmp) {
//    			System.out.println( str );
    			if(str.indexOf("item") > -1) {
    				System.out.println("전체 아이템 반복 부분 스킵.");
    				continue;
    			}
    			if(str.indexOf("dataType") > -1) {
    				System.out.println("바디 반복 부분 스킵.");
    				continue;
    			}
    			if(str.indexOf("header") > -1) {
    				System.out.println("헤더 반복 부분 스킵");
    				continue;
    			}
    			if(str.indexOf("response") > -1) {
    				System.out.println("전체 반복 부분 스킵");
    				continue;
    			}
    			
    			if(str.indexOf("resultCode") > -1) {
    				System.out.println("결과 코드값출력.");
    				if(str.indexOf("response") > -1) {
        				System.out.println("전체 반복 부분 스킵");
        			}
    			}
    			
    			if(str.indexOf("category") > -1 ) {
    				contentInfo.getResponse().getBody().getItems().add(new Item(str));
    			}
    		}
    		
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
        
    	
    	for(Item i : contentInfo.getResponse().getBody().getItems() ) {
//    		System.out.println( i.toString() );
    		switch( eCategory.forValue( i.getCategory() )){
			case _POP:
				System.out.print(" | "+ eCategory._POP + " : " + i.getFcstValue() );
				break;
			case _PTY:
				System.out.print(" | "+ eCategory._PTY + " : " + i.getFcstValue() );
				break;
			case _REH:
				System.out.print(" | "+ eCategory._REH + " : " + i.getFcstValue() );
				break;
			case _SKY:
				System.out.print(" | "+ eCategory._SKY + " : " + i.getFcstValue() );
				break;
			case _T3H:
				System.out.print(" | "+ eCategory._T3H + " : " + i.getFcstValue() );
				break;
			case _TMX:
				System.out.print(" | "+  eCategory._TMX + " : " + i.getFcstValue() );
				break;
			case _UUU:
				System.out.print(" | "+  eCategory._UUU + " : " + i.getFcstValue() );
				break;
			case _VEC:
				System.out.print(" | "+  eCategory._VEC + " : " + i.getFcstValue() );
				break;
			case _VVV:
				System.out.print(" | "+ eCategory._VVV + " : " + i.getFcstValue() );
				break;
			case _WSD:
				System.out.print(" | "+ eCategory._WSD + " : " + i.getFcstValue() );
				break;	
			}
    		
    	}
    	
    	System.out.println();
        System.out.println("HttpSnoopClientHandler | channelRead 동작끝 =====================================================");
        
        JSONParser parser = new JSONParser();
		//오브젝트에 담음
		Object obj = parser.parse( info );
		//Json 으로 변경
		JSONObject jsonObj = (JSONObject) obj;
	   
		System.out.println("obj : " + jsonObj);
		
		System.out.println("header : " + jsonObj.get("baseDate"));
		
		Gson g = new Gson();
		
		weatherAPIForm apiForm = new ObjectMapper().readValue(info, weatherAPIForm.class); 
        
        */
    	
    	
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	if(WebMap.getWebContentTmpByCTX(ctx) == null ) {
    		WebMap.setWebContentTmpByCTX(ctx);
    		System.out.println("키값( channelHandlerContext.channel().remoteAddress() )으로 찾을수 있는 요소가 없기 때문에 새로 생성");
    	}
		System.out.println("CTX : " + ctx.toString());
		
//        ctx.fireChannelActive();
        
        super.channelActive(ctx);
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}