package com.network.netty.codec;

import java.util.List;

import com.domain.WebContentTmp;
import com.service.WebMap;
import com.service.WebResponse;
import com.util.ApplicationContextProvider;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
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

public class WebDecoder extends HttpContentDecoder{

	private final boolean strict;

	private StringBuilder sb;
	
//	WebResponse webResponseMap = (WebResponse) ApplicationContextProvider.getApplicationContext().getBean("WebResponse");
		
//	WebMap webMap = (WebMap) ApplicationContextProvider.getApplicationContext().getBean(WebMap.class);

    public WebDecoder() {
        this(false);
    }

    public WebDecoder(boolean strict) {
        this.strict = strict;
    }
	
    @Override
    protected void decode(ChannelHandlerContext ctx, HttpObject msg, List<Object> out) throws Exception {
    	System.out.println("웹 응답 인코딩할때 여기탐 decode ");
    	
    	int contentLength = 0;
    	
    	if(sb == null) {
    		sb = new StringBuilder();
    	}
    	
    	if (msg instanceof HttpResponse) 
    	{
    		System.out.println("디코딩할때 HttpResponse 로  변환함.");

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
                        if(name.equals("Content-Length")) 
                        {
                        	contentLength = Integer.parseInt(value.toString());
                        	System.out.println("Length : "+ contentLength );
                        	if( WebMap.webContentMap.containsKey(ctx)) 
                        	{
                        		System.out.println("채널정보가 키값으로 있는 객체가 있음.");
                        		if( WebMap.webContentMap.get(ctx).getContentLength() == 0) 
                        		{
                        			int tmpLen = WebMap.webContentMap.get(ctx).getContentLength();
                        			System.out.println("채널 정보가 키값으로 있는 객체를 찾아냈고 객체의 컨텐츠 길이가 "+ tmpLen + "임");
                        			WebMap.webContentMap.get(ctx).setContentLength(contentLength);
                        			tmpLen = WebMap.webContentMap.get(ctx).getContentLength();
                        			System.out.println("객체 컨텐츠 길이 설정함 : " + tmpLen );
                        		}
                        	}else {
                        		System.out.println("채널정보가 키값으로 있는 객체가 없음. 리턴.");
                        		return;
                        	}
                        }
                    }
                }
            
                System.out.println();

            }
            else 
            {
            	System.out.println("Header is Empty");
            }
            
        }
    	
    	System.out.println(" Content-Length 세팅 확인용 : " + contentLength );
    	
    	if (msg instanceof HttpMessage) 
    	{
    		
    		System.out.println("디코딩할때 HttpMessage 로  변환함.");
    		
			final HttpMessage message = (HttpMessage) msg;
			final HttpHeaders headers = message.headers();
			
			System.out.println( "message toString : " +message.toString() );
             
			if (msg instanceof HttpContent) {
				System.out.println("디코딩할때 HttpMessage 안에서 httpconentent 로  변환함.");
				sb.append(  ((HttpContent) msg).content().toString(CharsetUtil.UTF_8)  );
				System.out.println(" sb.length = " + sb.toString().length() 
								 + " contentLength = " + contentLength
								  );
            }
    	}
    	
    	
    	
    	
    	if (msg instanceof HttpContent) {
    		System.out.println("디코딩 httpContent 형태로 변환 함");
    		
    		sb.append(  ((HttpContent) msg).content().toString(CharsetUtil.UTF_8)  );
    		
    		System.out.println(" sb.length = " + sb.toString().length()  
					 		 + " contentLength = " + contentLength
					 		  );
    	}
    	
//    	out.add( (HttpResponse) msg); //에러
    	
    	WebContentTmp trans = new WebContentTmp(); 
    	
    	trans.setCtx(ctx);
    	trans.setContentLength(contentLength);
    	trans.setSb(sb);
    	System.out.println("trans info : " + trans.toString() );

//    	out.add(trans);  //과거의 내가 왜 썼는지는 모르겠지만 이렇게 쓰면 안됨.
    	
    	super.decode(ctx, msg, out);
    	
//    	if(sb.toString().length() >= contentLength ) {
//    		super.decode(ctx, msg, out);
//    		sb = null;
//    	}else {
//    		System.out.println("길이 안맞아서 리턴함");
//    		return;
//    	}
    	    	
    }
    
    
    //지정된 인코딩이 지원되는 경우 신규 null , 그렇지 않으면 (또는 예외를 발생시켜 알 수없는 인코딩을 차단할 수 있음)
	@Override
	protected EmbeddedChannel newContentDecoder(String contentEncoding) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("웹 응답 디코딩할때 여기탐 newContentDecoder   contentEncoding : " + contentEncoding);
		
		return null;
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		if( ! WebMap.webContentMap.containsKey(ctx) ) {
			System.out.println("맵에 있는 채널 정보와 매칭되는 객체가 없음. 새로 생성함.");
			WebMap.webContentMap.put(ctx,new WebContentTmp(ctx) );
		}
		
		// TODO Auto-generated method stub
		super.channelActive(ctx);
	}

}
