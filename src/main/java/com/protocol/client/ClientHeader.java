package com.protocol.client;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.domain.WebRequestTemplate;
import com.domain.functionFcstVersion;
import com.domain.functionVilageFcst;
import com.enums.eWeb;
import com.protocol.Message;

import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;

public class ClientHeader implements Message{
		
	/*
	 * HTTP 공통 헤더
			HTTP 헤더 내 일반 헤더 (General Header) 항목
			
			요청 및 응답 메시지 모두에서 사용 가능한 일반 목적의(기본적인) 헤더 항목
			주요 항목들
	Date
			HTTP 메시지를 생성한 일시 (RFC 1123에서 규정)
			Date: Sat, 2 Oct 2018 02:00:12 GMT
	
	Connection
			클라이언트와 서버 간 연결에 대한 옵션 설정(다소 모호한 복잡성 있음)
			Ex) Connection: close
			현재 HTTP 메시지 직후에 TCP 접속을 끊는다는 것을 알린다.
			Ex) Connection: Keep-Alive
			현재 TCP 커넥션을 유지한다.

	Cache-Control
			(쿠키/캐시 관련)

	Pragma
	
	Trailer
			https://gmlwjd9405.github.io/2019/01/28/http-header-types.html
	 * 
	 *  HTTP엔티티 관련 헤더
	Content-Type
			해당 개체에 포함되는 미디어 타입 정보
			컨텐츠의 타입(MIME 미디어 타입) 및 문자 인코딩 방식(EUC-KR,UTF-8 등)을 지정한다.
			타입 및 서브타입(type/subtype)으로 구성된다.
			타입(type): 10개 정도 표준으로 지정됨(application, audio, font, image, multipart 등)
			서브타입(subtype): 각 타입별로 수십에서 수백개 정도
			Ex) Content-Type: text/html; charset-latin-1
			해당 개체가 html 텍스트 문서이고, iso-latin-1 문자 인코딩 방식으로 표현되는 것을 의미한다.
			(공통 헤더)
			
	Content-Language
			해당 개체와 가장 잘 어울리는 사용자 언어(자연언어)
			(공통 헤더)
			
	Content-Encoding
			해당 개체 데이터의 압축 방식
			Ex) Content-Encoding: gzip, deflate
			만일 압축이 시행되었다면 Content-Encoding 및 Content-Length 2개 항목을 토대로 압축 해제가 가능하다.
			(공통 헤더)
			
	Content-Length
			전달되는 해당 개체의 바이트 길이 또는 크기(10진수)
			응답 메시지 Body의 길이를 지정하거나 특정 지정된 개체의 길이를 지정한다.
			(공통 헤더)
	
	Content-Location
			해당 개체의 실제 위치를 알려준다.
			(공통 헤더)
	
	Content-Disposition
			응답 Body를 브라우저가 어떻게 표시해야할지 알려준다.
			inline인 경우 웹페이지 화면에 표시되고, attachment인 경우 다운로드한다.
			Ex) Content-Disposition: inline
			Ex) Content-Disposition: attachment; filename='filename.csv'
			다운로드되길 원하는 파일은 attachment로 값을 설정하고, filename 옵션으로 파일명까지 지정해줄 수 있다.
			파일용 서버인 경우 이 태그를 자주 사용한다.
			(응답 헤더)
	
	Content-Security-Policy
			다른 외부 파일들을 불러오는 경우, 차단할 소스와 불러올 소스를 명시한다.
			XSS 공격에 대한 방어 가능 (허용한 외부 소스만 지정 가능)
			Ex) Content-Security-Policy: default-src https:
			https를 통해서만 파일을 가져온다.
			Ex) Content-Security-Policy: default-src 'self'
			자신의 도메인의 파일들만 가져온다.
			Ex) Content-Security-Policy: default-src 'none'
			파일을 가져올 수 없다.
			(응답 헤더)
	
	Location
			리소스가 리다이렉트(redirect)된 때에 이동된 주소, 또는 새로 생성된 리소스 주소를 명시한다.
			300번대 응답이나 201 Created 응답일 때 어느 페이지로 이동할지를 알려준다.
			새로 생성된 리소스의 경우
			HTTP 상태 코드 201 Created가 반환된다.
			300번대 응답의 경우
			HTTP/1.1 302 Found Location: /
			이런 응답이 왔다면 브라우저는 / 주소로 redirect한다.
			(응답 헤더)
	
	Last-Modified
			리소스를 마지막으로 갱신한 일시
			(응답 헤더)
	
	Transfer-Encoding
			chuncked
			동적으로 생성되어 Body의 길이를 모르는 경우에 조금씩 전송이 가능하다.
			각 chunk 마다 그 시작에 16진수 길이를 삽입하여 chunk 길이를 알려준다.
			(응답 헤더)
			https://gmlwjd9405.github.io/2019/01/28/http-header-types.html
		
			*HTTP 요청 헤더
			HTTP 헤더 내 요청 헤더 (Request Header) 항목
			
			요청 헤더는 HTTP 요청 메시지 내에서만 나타나며 가장 방대하다.
			주요 항목들
			
	Host
			요청하는 호스트에 대한 호스트명 및 포트번호 (필수)
			HTTP/1.1 이후부터 Host 필드는 필수 항목이다. (웹브라우저는 이를 반드시 포함해야 함)
			Host 필드에 도메인명 및 호스트명 모두를 포함한 전체 URI(FQDN) 지정이 필요하다.
			이에 따라 동일 IP 주소를 갖는 단일 서버에 여러 사이트를 구축할 수 있다.
	
	User-Agent
			클라이언트 소프트웨어(브라우저, OS) 명칭 및 버전 정보
	
	From
			클라이언트 사용자 메일 주소
			주로 검색엔진 웹 로봇의 연락처 메일 주소를 나타낸다.
			때로는, 이 연락처 메일 주소를 User-Agent 항목에 두는 경우도 있다.
	
	Cookie
			서버에 의해 Set-Cookie로 클라이언트에게 설정된 쿠키 정보
			(쿠키/캐시 관련)
	
	Referer
			바로 직전에 머물었던 웹 링크 주소
			
	If-Modified-Since
			제시한 일시 이후로만 변경된 리소스를 취득 요청
	
	Authorization
			인증 토큰(JWT/Bearer 토큰)을 서버로 보낼 때 사용하는 헤더
			“토큰의 종류(Basic, Bearer 등) + 실제 토큰 문자”를 전송
	
		Accept
			클라이언트 자신이 원하는 미디어 타입 및 우선순위를 알린다.
			텍스트(text/html,text/plain,…),이미지(image/jpeg,…) 등
			Ex) Accept: * / *
			어떤 미디어 타입도 가능하다.
			Ex) Accept: image / *
			모든 이미지 유형이 가능하다.
		Accept-Charset
			클라이언트 자신이 원하는 문자 집합
		Accept-Encoding
			클라이언트 자신이 원하는 문자 인코딩 방식
		Accept-Language
			클라이언트 자신이 원하는 가능한 언어
			각각이 HTTP Entity Header 항목 중에 Content-Type, Content-Type charset-xxx, Content-Encoding, Content-Language과 일대일로 대응된다.
*/
	
//	초단기실황조회
//	http://apis.data.go.kr/1360000/VilageFcstInfoService/getUltraSrtNcst
//		?serviceKey=인증키&numOfRows=10&pageNo=1
//		&base_date=20151201&base_time=0600&nx=55&ny=127

//	초단기예보조회
//	http://apis.data.go.kr/1360000/VilageFcstInfoService/getUltraSrtFcst
//		?serviceKey=인증키&numOfRows=10&pageNo=1
//		&base_date=20151201&base_time=0630&nx=55&ny=127

//	동네예보조회
//	http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst
//		?serviceKey=인증키&numOfRows=10&pageNo=1
//		&base_date=20151021&base_time=0230&nx=55&ny=127

//	예보버전조회
//	http://apis.data.go.kr/1360000/VilageFcstInfoService/getFcstVersion
//		?serviceKey=인증키&numOfRows=10&pageNo=1
//		&ftype=ODAM&basedatetime=201701170800
	
	
	String endPoint = "http://apis.data.go.kr:80/1360000/VilageFcstInfoService";
//	String endPoint;

//	String autorithyKey = "fW24b%2FUEGRWODAIUo6Nx0owzv7jQSggx5iSzj0JeQY4z1UHlo4spwka5vM4XeuRMLpKwg6a%2B%2F%2Bu65jtdoFl67g%3D%3D";
//	String APIKey;
	String APIKey = "fW24b%2FUEGRWODAIUo6Nx0owzv7jQSggx5iSzj0JeQY4z1UHlo4spwka5vM4XeuRMLpKwg6a%2B%2F%2Bu65jtdoFl67g%3D%3D";
	
	String dataType;
	functionFcstVersion fcstVersion = null;
	functionVilageFcst vilageFcst = null;
	
	String function;
	String method;
	
	WebRequestTemplate webTmp;
	
	HttpHeaders headers;    
	HttpRequest httpRequest;
	
	public ClientHeader() { }
	
	public ClientHeader(  String function, WebRequestTemplate webTmp, String dataType, String method  ) 
	{
			this.function	=	function;
			this.webTmp		=	webTmp;
			this.dataType	=	dataType;
			this.method		=	method;

			StringBuilder sb = new StringBuilder();
			
			webTmp.setAPIKey(this.APIKey);
			
			sb.append( this.endPoint );
			sb.append( "/" );
			sb.append( this.function );
			sb.append( "?serviceKey=" );
			sb.append( this.webTmp.getAPIKey() );
			sb.append( "&pageNo=" );
			sb.append( this.webTmp.getPageNo() );
			sb.append( "&numOfRows=" );
			sb.append( this.webTmp.getNumOfRows() );
			
			if(function.equalsIgnoreCase(eWeb.getVilageFcst.toString()) ) 
			{
			sb.append("&ftype=");
			sb.append("ODAM"); // 몰라 시간 너무걸린다 대충 여기서 끝내 박아넣고 데이터 받는거 하러가야지
			sb.append("&basedatetime=");
			sb.append(this.webTmp.getDateDay());
			sb.append(this.webTmp.getDatetime());
			}
			else 
			{
			sb.append("&base_date=");
			sb.append(this.webTmp.getDateDay());
			sb.append("&base_time=");
			sb.append(this.webTmp.getDatetime());
			sb.append("&nx=");
			sb.append(this.webTmp.getX());
			sb.append("&ny=");
			sb.append(this.webTmp.getY());
			}
			
			this.httpRequest = new DefaultFullHttpRequest(
										HttpVersion.HTTP_1_1,
										(this.method.equalsIgnoreCase("get")? HttpMethod.GET : 
											(this.method.equalsIgnoreCase("post")? HttpMethod.POST : 
												(this.method.equalsIgnoreCase("put")? HttpMethod.PUT : HttpMethod.DELETE)
											)
										),
										sb.toString()
										);
			
			}
	
	public ClientHeader(  String APIKey
						, String endPoint
						, String function
						, WebRequestTemplate webTmp
						, String dataType
						, String method  ) {
		this.APIKey		=	APIKey;
		this.endPoint	=	endPoint;
//		if( function instanceof functionFcstVersion) 
//			this.fcstVersion = (functionFcstVersion) function; 
//		else if ( function instanceof functionVilageFcst) 
//			this.vilageFcst = (functionVilageFcst) function;
		
//		http://apis.data.go.kr/1360000/VilageFcstInfoService/getFcstVersion?
//		serviceKey=fW24b%2FUEGRWODAIUo6Nx0owzv7jQSggx5iSzj0JeQY4z1UHlo4spwka5vM4XeuRMLpKwg6a%2B%2F%2Bu65jtdoFl67g%3D%3D
//		&pageNo=1
//		&numOfRows=10
//		&dataType=XML
//		&ftype=ODAM
//		&basedatetime=202012091000
		
		this.function	=	function;
		this.webTmp		=	webTmp;
		this.dataType	=	dataType;
		this.method		=	method;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append( this.endPoint );
		sb.append( "/" );
		sb.append( this.function );
		sb.append( "?serviceKey=" );
		sb.append( this.webTmp.getAPIKey() );
		sb.append( "&pageNo=" );
		sb.append( this.webTmp.getPageNo() );
		sb.append( "&numOfRows=" );
		sb.append( this.webTmp.getNumOfRows() );
		
		if(function.equalsIgnoreCase("getVilageFcst") ) {
			sb.append("&ftype=");
			sb.append("ODAM"); // 몰라 시간 너무걸린다 대충 여기서 끝내 박아넣고 데이터 받는거 하러가야지
			sb.append("&basedatetime=");
			sb.append(this.webTmp.getDateDay());
			sb.append(this.webTmp.getDatetime());
			
		}else {
			sb.append("&base_date=");
			sb.append(this.webTmp.getDateDay());
			sb.append("&base_time=");
			sb.append(this.webTmp.getDatetime());
			sb.append("&nx=");
			sb.append(this.webTmp.getX());
			sb.append("&ny=");
			sb.append(this.webTmp.getY());
			
		}
		
		this.httpRequest = new DefaultFullHttpRequest(
										HttpVersion.HTTP_1_1,
										(this.method.equalsIgnoreCase("get")? HttpMethod.GET : 
											(this.method.equalsIgnoreCase("post")? HttpMethod.POST : 
												(this.method.equalsIgnoreCase("put")? HttpMethod.PUT : HttpMethod.DELETE)
											)
										),
										sb.toString()
										);
		
	}
		
	
	public String toString() {
		String aa = " webTmp.toString() : " + webTmp.toString() 
					+ " | function : " + function 
					+ " | method : " + method; 
		
		return aa;
	}
	
	public HttpRequest getHttpRequest() {
		return this.httpRequest;
	}


	/*
	public ClientHeader(byte len, byte sender, byte opcode, byte status) {
		this.len = len;
		this.sender = sender;
		this.opcode = opcode;
		this.status = status;
	}
	
	public ClientHeader(int len, int sender, int opcode, int status) {
		this.len = (byte)len;
		this.sender = (byte)sender;
		this.opcode = (byte)opcode;
		this.status = (byte)status;
	}
	*/	
	
	@Override
	public byte[] array(ByteOrder order) {
		// TODO Auto-generated method stub
		ByteBuffer buffer = ByteBuffer.allocate(this.size());
		buffer.order(order);
		
		return buffer.array();
	}

	public byte[] defaultHeader() {
		// TODO Auto-generated method stub
		ByteBuffer buffer = ByteBuffer.allocate(this.size());
		buffer.order(ByteOrder.BIG_ENDIAN);
		return buffer.array();
	}
	@Override
	public void parse(byte[] array, ByteOrder order) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void parse(byte[] array) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
