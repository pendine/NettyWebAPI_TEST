package com.enums;

import java.util.HashMap;
import java.util.Map;

public enum eHttpStatusCode {

	  _REQUEST_SUCCESS("CAMERA_CONN_SUCCESS" , 200)	//	요청이 성공적으로 완료 
	, _WRONG_METHOD_TYPE("WRONG_METHOD_TYPE" , 450 )//	REST API의 Method 타입이 잘못되어 있음
	, _LOGIN_FAIL("LOGIN_FAIL" , 451 )				//	로그인에 실패함
	, _INVALID_APIKEY("INVALID_APIKEY" , 452 )		//	유효하지 않은 API KEY값을 사용함
	, _INVALID_BODY_FORM("INVALID_BODY_FORM" , 453 )//	유효하지 않은 Body 양식
	;
	
	//	HTTP 상태 코드		설명
	//	200				요청이 성공적으로 완료되었습니다.
	//---------------------------------------------------
	//	450				REST API의 Method 타입이 잘못되어 있음
	//	451				로그인에 실패함
	//	452				유효하지 않은 API KEY값을 사용함
	//	453				유효하지 않은 Body 양식

	   
	private final String value;
	private final int intValue;

	private final static Map<String, eHttpStatusCode> map = new HashMap<String, eHttpStatusCode>();
	static {
		for (eHttpStatusCode str : values())
			map.put(str.getValue() , str);
	}

	private eHttpStatusCode(String value, int intValue) {
		this.value = value;
		this.intValue = intValue;
	}

	public static eHttpStatusCode forValue(String value) {
		return map.get(value);
	}
	
	public String getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}