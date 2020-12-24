package com.enums;

import java.util.HashMap;
import java.util.Map;

public enum eCameraStatus {

	  _CAMERA_CONN_SUCCESS("CAMERA_CONN_SUCCESS" , 0x00000001)	//	카메라 연결 성공
	, _CAMERA_CONN_FAIL("CAMERA_CONN_FAIL" , 0x00000002 )		//	카메라 연결 실패
	;
	
//	카메라 상태			값 (16진수)	설명
//	카메라 연결 성공		0x00000001	카메라가 연결됨
//	카메라 연결 실패		0x00000002	카메라가 끊어짐
	   
	private final String value;
	private final int intValue;

	private final static Map<String, eCameraStatus> map = new HashMap<String, eCameraStatus>();
	static {
		for (eCameraStatus str : values())
			map.put(str.getValue() , str);
	}

	private eCameraStatus(String value, int intValue) {
		this.value = value;
		this.intValue = intValue;
	}

	public static eCameraStatus forValue(String value) {
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