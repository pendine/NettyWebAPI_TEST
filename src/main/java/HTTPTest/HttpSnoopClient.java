package HTTPTest;
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

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.cookie.ClientCookieEncoder;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

import java.net.URI;

import com.service.WebMap;

/**
 * A simple HTTP client that prints out the content of the HTTP response to
 * {@link System#out} to test {@link HttpSnoopServer}.
 */
public final class HttpSnoopClient {

	static final String JVMVER = System.getProperty("java.vm.version");
	static final String VERSION = System.getProperty("java.version");
	
	static ChannelFuture cf;
	
	
    static final String URL = System.getProperty("url", "https://google.com:443/");
    
    static public String weather = System.getProperty("url", "http://apis.data.go.kr/1360000/VilageFcstInfoService");
    
    static String DeafualtUrl = "/1360000/VilageFcstInfoService" ;
    static String viewVilageFcst = "/getVilageFcst";
    static String serviceKey = "?serviceKey=";
    static String APIKey = "fW24b%2FUEGRWODAIUo6Nx0owzv7jQSggx5iSzj0JeQY4z1UHlo4spwka5vM4XeuRMLpKwg6a%2B%2F%2Bu65jtdoFl67g%3D%3D";
    
    static String service = serviceKey + APIKey;
    
    static String numOfRaws = "&numOfRows=";
    static int numOfRawsInt = 10;
    
    static String pageNo = "&pageNo=";
    static int pageNoInt = 1;
    
    static String dataType = "&dataType=";
    static String resultType = "JSON";
    
    static String base_date = "&base_date=";
    static String base_dateInt = "20201214";
    
    static String base_time = "&base_time=";
    static String base_timeInt = "0500"; 
    
    
    static String nx = "&nx=";
    static int nxInt = 55;
    
    static String ny = "&ny="; 
    static int nyInt = 127;
    
    public static void main(String[] args) throws Exception {
//        URI uri = new URI(URL);
    	
//    	URI uri = new URI(weather);
    	
    	String aaa = weather.toString() 
    				+ viewVilageFcst 
    				+ service 
    				+ pageNo + pageNoInt
    				+ numOfRaws + numOfRawsInt
    				+ dataType  + resultType
    				+ base_date + base_dateInt
    				+ base_time + base_timeInt
    				+ nx + nxInt
    				+ ny + nyInt;
    	
    	
//    	String aaa = 
//    	"http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?serviceKey=fW24b%2FUEGRWODAIUo6Nx0owzv7jQSggx5iSzj0JeQY4z1UHlo4spwka5vM4XeuRMLpKwg6a%2B%2F%2Bu65jtdoFl67g%3D%3D&pageNo=1&numOfRows=10&dataType=JSON&base_date=20201214&base_time=0500&nx=55&ny=127";
    	
    	System.out.println(" aaa : " + aaa);
    	
        WebMap webMap = new WebMap();
        
    	URI uri = new URI(aaa);
    	
        System.out.println("Java VM.Ver : " + JVMVER );
        System.out.println("Java VERSION : " + VERSION );
        System.out.println("Scheme : "+uri.getScheme());
        System.out.println("Host : "+uri.getHost());
        System.out.println("Port : "+uri.getPort());
        
        
        String scheme = uri.getScheme() == null? "http" : uri.getScheme();
        String host = uri.getHost() == null? "127.0.0.1" : uri.getHost();
                
        int port = uri.getPort();
        if (port == -1) 
        {
            if ("http".equalsIgnoreCase(scheme)) 
            {
                port = 80;
            } 
            else if ("https".equalsIgnoreCase(scheme)) 
            {
                port = 443;
            }
        }

        if (!"http".equalsIgnoreCase(scheme) && !"https".equalsIgnoreCase(scheme)) {
            System.err.println("Only HTTP(S) is supported.");
            return;
        }

        // Configure SSL context if necessary.
        final boolean ssl = "https".equalsIgnoreCase(scheme);
        final SslContext sslCtx;
        if (ssl) {
            sslCtx = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        } else {
            sslCtx = null;
        }

        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioSocketChannel.class)
             .handler(new HttpSnoopClientInitializer(sslCtx));

            cf = b.connect(host, port);
            // Make the connection attempt.
            Channel ch = cf.sync().channel();
//            Channel ch = b.connect(host, port).sync().channel();

            // Prepare the HTTP request.
            HttpRequest request = new DefaultFullHttpRequest(
                    HttpVersion.HTTP_1_1
                    , HttpMethod.GET
//                    , uri.getRawPath()
                    , aaa
//                    , Unpooled.EMPTY_BUFFER
                    );
            
            
            
            //야... 이걸 주석처리 하니까 웹페이지 모든 소스를 불러와버린다... 개꿀!
            //위에게 아니라 뭔가 다른걸 불러옴. 헤더셋팅을 안했는데 불러올수있을리가 없지.
            request.headers().set(HttpHeaderNames.HOST, host);
            
            
            request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
            
            //야 인코딩 안해도 연결은 되는 것 같다.
//            request.headers().set(HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderValues.GZIP);
            //이쪽 컨텐츠 인코딩타입에서 오류남.
//            request.headers().set(HttpHeaderNames.CONTENT_ENCODING, HttpHeaderValues.GZIP);
            
//            request.headers().set();
            
            //인코딩 타입은 안맞춰도 오류는 없음.
            request.headers().set(HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderValues.APPLICATION_JSON);
            request.headers().set(HttpHeaderNames.CONTENT_ENCODING, HttpHeaderValues.APPLICATION_JSON);
            

            //야 쿠키가 안들어가도 된다.
            // Set some example cookies.
//            request.headers().set(
//                    HttpHeaderNames.COOKIE,
//                    ClientCookieEncoder.STRICT.encode(
//                            new DefaultCookie("my-cookie", "foo"),
//                            new DefaultCookie("another-cookie", "bar")));

            // Send the HTTP request.
            

            
            System.out.println("channelFuture is Done : "		+ cf.isDone());
            System.out.println("channelFuture is Success : "	+ cf.isSuccess());
            System.out.println("channelFuture is Cancelled : "	+ cf.isCancelled());
            System.out.println("channelFuture is Void : "		+ cf.isVoid());
            
            System.out.println("클라이언트 생성 시 채널로 데이터 보낼 예정이다.");
            ch.writeAndFlush(request);
            System.out.println("클라이언트 생성 시 채널로 데이터 보내버렸다.");
            
            System.out.println("channelFuture is Done : "		+ cf.isDone());
            System.out.println("channelFuture is Success : "	+ cf.isSuccess());
            System.out.println("channelFuture is Cancelled : "	+ cf.isCancelled());
            System.out.println("channelFuture is Void : "		+ cf.isVoid());
            // Wait for the server to close the connection.
            ch.closeFuture().sync();
            System.out.println("채널 닫음.");
        } 
        finally 
        {
            // Shut down executor threads to exit.
            group.shutdownGracefully();
        }
    }
}