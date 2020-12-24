package com.network.netty.codec;

import java.util.List;

import com.domain.WebContentTmp;
import com.service.WebMap;
import com.service.WebResponse;
import com.util.ApplicationContextProvider;
import com.util.NettyHelper;

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
	
	//패킷 변환이 하나에서 모두 일어나는지 확인용.
	int single = 0;
	
//	WebResponse webResponseMap = (WebResponse) ApplicationContextProvider.getApplicationContext().getBean("WebResponse");
	
	
    public WebDecoder() {
        this(false);
    }
    
    public WebDecoder(boolean strict) {
        this.strict = strict;
    }
    
    @Override
    protected void decode(ChannelHandlerContext ctx, HttpObject msg, List<Object> out) throws Exception {
    	System.out.println("[웹 응답 인코딩할때 여기탐 decode  시작 =====================================================]  single : " + single );
    	
    	System.out.println("WebDecoder | decode | 채널값 확인용 ");
    	System.out.println("ctx : "+ctx.toString() + " ip : "+ NettyHelper.getRemoteAddress( ctx.channel() ) );
    	System.out.println("ctx.channel id : " + ctx.channel().id());
    	System.out.println("ctx.name : " + ctx.name());
    	
    	int contentLength = 0;
    	
    	if(sb == null) {
    		sb = new StringBuilder();
    	}
    	
    	if (msg instanceof HttpResponse) 
    	{
    		System.out.println("[=====디코딩할때 HttpResponse 로  변환 시작 부분.=======================] single : " + single );
    		single = 1;
    		
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
                        	contentLength = Integer.parseInt( value.toString() );
                        	System.out.println("Length : "+ contentLength );
                        	if( WebMap.webContentMap.containsKey(ctx)) 
                        	{
                        		System.out.println("채널정보가 키값으로 있는 객체가 있음.");
                        		if( WebMap.webContentMap.get(ctx).getContentLength() == 0) 
                        		{
                        			int tmpLen = WebMap.webContentMap.get(ctx).getContentLength();
                        			System.out.println("채널 정보가 키값으로 있는 객체를 찾아냈고 객체의 컨텐츠 길이가 0 이므로 재설정대상임.");
                        			WebMap.webContentMap.get(ctx).setContentLength(contentLength);
                        			tmpLen = WebMap.webContentMap.get(ctx).getContentLength();
                        			System.out.println("객체 컨텐츠 길이 설정함 : " + tmpLen );
                        		}
                        	}
                        	else 
                        	{
                        		System.out.println("채널정보가 키값으로 있는 객체가 없음. 리턴.");
                        		return;
                        	}
                        }
                    }
                }
            }
            else 
            {
            	System.out.println("Header is Empty");
            }
            
    		System.out.println("[=====디코딩할때 HttpResponse 로  변환 끝부분.=======================] single : " + single );
    		single = 2;
    		
        }
    	
    	if (msg instanceof HttpMessage) 
    	{
    		
    		System.out.println("[=====디코딩할때 HttpMessage 로  변환 시작부분.=======================] single : " + single );
    		single = 3;
    		
    		
    		if( WebMap.webContentMap.containsKey(ctx)) 
        	{
        		System.out.println("채널정보가 키값으로 있는 객체가 있음. 컨텐츠 길이 : " + WebMap.webContentMap.get(ctx).getContentLength());
        	}else {
        		System.out.println("채널정보가 키값으로 있는 객체가 없음. 리턴.");
        		return;
        	}
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
    		
	    	System.out.println("[=====디코딩할때 HttpMessage 로  변환  끝 부분.=======================] single : " + single );
    		single = 4;
    		
    	}
    	
    	if (msg instanceof HttpContent) {
    		
    		System.out.println("[=====디코딩할때 HttpContent 로  변환 시작부분.======================= single : " + single );
    		single = 5;
    		
    		System.out.println("디코딩 httpContent 형태로 변환 함");
    		
    		if( WebMap.webContentMap.containsKey(ctx)) 
        	{
        		System.out.println("채널정보가 키값으로 있는 객체가 있음. 컨텐츠 길이 : " + WebMap.webContentMap.get(ctx).getContentLength() + " 문자열 길이 : " + WebMap.webContentMap.get(ctx).getSb().toString().length() );
        		
        		if(WebMap.webContentMap.get(ctx).getContentLength() > WebMap.webContentMap.get(ctx).getSb().toString().length() ) {
        			System.out.println("컨텐츠 길이보다 짧으므로 덧붙이기~");
	        		WebMap.webContentMap.get(ctx).getSb().append( ((HttpContent) msg).content().toString(CharsetUtil.UTF_8) );
	        		System.out.println("키캆으로 객체 정보에 설정한 객체 내용 :");
	        		System.out.println( WebMap.webContentMap.get(ctx).getSb().toString() );
	        		System.out.println("해당 컨텐츠의 길이 : " + WebMap.webContentMap.get(ctx).getContentLength() 
	        				+ " 받은 문자열 총 길이 : " + WebMap.webContentMap.get(ctx).getSb().toString().length() );
	        		try 
	        		{
		        		String resultcode = findStringValue( "\"" , WebMap.webContentMap.get(ctx).getSb().toString() , "resultCode" );
		        		WebMap.webContentMap.get(ctx).getResponse().getHeader().setResultCode(resultcode);
	
		        		String resultMsg = findStringValue( "\"" , WebMap.webContentMap.get(ctx).getSb().toString() , "resultMsg" );
		        		WebMap.webContentMap.get(ctx).getResponse().getHeader().setResultMsg(resultMsg);
		        		
		        		System.out.println("짧음");
//		        		return;
//		        		System.out.println("짧으니까 리턴안하고 디코딩");
//		        		super.decode(ctx, msg, out);
		        		
	        		}
	        		catch(Exception e)
	        		{
	        			e.printStackTrace();
	        		}
	        		
        		}
        		
//        		if( !WebMap.webContentMap.get(ctx).getResponse().getHeader().getResultcode().equals("00") ) {
//        			System.out.println("정상 코드가 아님! return!");
//        			System.out.println("현재 코드값 : " + WebMap.webContentMap.get(ctx).getResponse().getHeader().getResultcode() );
//        			return;
//        		}
        		
        		if(WebMap.webContentMap.get(ctx).getContentLength() <= WebMap.webContentMap.get(ctx).getSb().toString().length())
        		{
        			System.out.println("컨텐츠 길이보다 길거나 같음");
        			System.out.println("ㅁㅁㅁ해당 컨텐츠의 길이 : " + WebMap.webContentMap.get(ctx).getContentLength() 
	        				+ " 받은 문자열 총 길이 : " + WebMap.webContentMap.get(ctx).getSb().toString().length() );
        			System.out.println("컨텐츠 길이보다 길거나 같으므로 다음 메소드인 Read로 넘겨버리기");
        			super.decode(ctx, msg, out);
        			System.out.println("넘김 완료");
        		}
        	}else {
        		System.out.println("채널정보가 키값으로 있는 객체가 없음. 리턴.");
        		return;
        	}
    		
    		sb.append(  ((HttpContent) msg).content().toString(CharsetUtil.UTF_8)  );
    		
    		System.out.println(" sb.length = " + sb.toString().length()  + " contentLength = " + contentLength );

    		System.out.println("[=====디코딩할때 HttpContent 로  변환 끝부분.=======================] single : " + single);
    		single = 6;

    	}
    	
    	WebContentTmp trans = new WebContentTmp(); 
    	
    	trans.setCtx(ctx);
    	trans.setContentLength(contentLength);
    	trans.setSb(sb);
    	System.out.println("trans info : " + trans.toString() );

    	
//    	if(sb.toString().length() >= contentLength ) {
//    		super.decode(ctx, msg, out);
//    		sb = null;
//    	}else {
//    		System.out.println("길이 안맞아서 리턴함");
//    		return;
//    	}
    	
    	System.out.println("[웹 응답 인코딩할때 여기탐 decode  끝=====================================================] single : " + single);

//    	super.decode(ctx, msg, out);
    	
    }
    
    
    //지정된 인코딩이 지원되는 경우 신규 null , 그렇지 않으면 (또는 예외를 발생시켜 알 수없는 인코딩을 차단할 수 있음)
	@Override
	protected EmbeddedChannel newContentDecoder(String contentEncoding) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("웹 응답 디코딩할때 여기탐 newContentDecoder   contentEncoding : " + contentEncoding);
		
		return null;
	}
	
	public String findStringValue( String seperate, String totalStr, String target) {
		int start = totalStr.indexOf(seperate, totalStr.indexOf(target)+ target.length() + 1); 
		int end	  = totalStr.indexOf(seperate,start+1);
		
		return totalStr.substring(start+1, end);
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		if( ! WebMap.webContentMap.containsKey(ctx) ) 
		{
			System.out.println("맵에 있는 채널 정보와 매칭되는 객체가 없음. ");
			System.out.println("WebDecoder | channelActive | 채널값 확인용 | 새로 생성함.");
	    	System.out.println("ctx : "+ctx.toString() + " ip : "+ NettyHelper.getRemoteAddress( ctx.channel() ) );
	    	System.out.println("ctx.channel id : " + ctx.channel().id());
	    	System.out.println("ctx.name : " + ctx.name());
	    	
			WebMap.webContentMap.put(ctx,new WebContentTmp(ctx) );
		}
		else
		{
			System.out.println("WebDecoder | channelActive | 채널값 확인용 | 이미 있음");
	    	System.out.println("ctx : "+ctx.toString() + " ip : "+ NettyHelper.getRemoteAddress( ctx.channel() ) );
	    	System.out.println("ctx.channel id : " + ctx.channel().id());
	    	System.out.println("ctx.name : " + ctx.name());
		}
		
		// TODO Auto-generated method stub
		super.channelActive(ctx);
	}

}
