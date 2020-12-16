package HTTPTest;

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
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.util.CharsetUtil;

public class HttpSnoopClientHandler extends SimpleChannelInboundHandler<HttpObject> {
	static int contentLength = 0;
//	WebResponse webResponseMap = (WebResponse) ApplicationContextProvider.getApplicationContext().getBean(WebResponse.class);
//	WebMap webMap = new WebMap();
	
//	WebMap webMap = (WebMap) ApplicationContextProvider.getApplicationContext().getBean(WebMap.class);
//	WebMap webMap = (WebMap) ApplicationContextProvider.getApplicationContext().getBean("webMap");
	
    @Override
    public void channelRead0(ChannelHandlerContext ctx, HttpObject msg) {
    	
    	HttpContent content = null;
    	StringBuilder sb = new StringBuilder();
    	int contentLength = 0;
    	System.out.println("channelRead 동작 =====================================================");
    	
        if (msg instanceof HttpResponse) {
            HttpResponse response = (HttpResponse) msg;

            System.out.println("STATUS: " + response.status());
            System.out.println("VERSION: " + response.protocolVersion());
            System.out.println();

            if (!response.headers().isEmpty()) 
            {
                for (CharSequence name: response.headers().names()) 
                {
                    for (CharSequence value: response.headers().getAll(name)) 
                    {
                        System.out.println("HEADER: " + name + " = " + value);
                        if(name.equals("Content-Length")) {
                        	contentLength = Integer.parseInt(value.toString());
                        	System.out.println("Length : "+ contentLength );
                        }
                    }
                }
                
                System.out.println();
                
            }
            else 
            {
            	System.out.println("Header is Empty");
            }

            if (HttpUtil.isTransferEncodingChunked(response)) 
            {
                System.out.println("CHUNKED CONTENT { ");
            }
            else 
            {
                System.out.println("CONTENT { ");
            }
            
        }
        
        if (msg instanceof HttpContent) {
            content = (HttpContent) msg;

            System.out.print(content.content().toString(CharsetUtil.UTF_8));
            sb.append( content.content().toString(CharsetUtil.UTF_8) );
            System.out.flush();
            
//			content.
//            System.out.flush();
//			System.out.println();

            if (content instanceof LastHttpContent) {
                System.out.println(" } END OF CONTENT");
                ctx.close();
            }
            
        }
        
        if(contentLength <= sb.toString().length()) {
        	System.out.println("ctn length : " + contentLength + " sb length : "+sb.toString().length());
	        System.out.println("sb : " + sb.toString());
        }
        

//        System.out.println(" ");
//        
//        String context = content.content().toString(CharsetUtil.UTF_8);
//        System.out.println("context : " + context);
//        int itemIndex = context.indexOf("item");
//        int itemStart = context.indexOf( "[" , itemIndex);
//        int itemEnd = context.indexOf("]" , itemIndex );
//        
//        System.out.println(" item Index : " + itemIndex + " itemStart '[' : " + itemStart + " itemEnd ']' : " + itemEnd );
//        
//        String tmp;
//        
//        tmp = context.substring(itemStart, itemEnd);
//        
//        System.out.println("tmp : " + tmp);
        
        
        
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