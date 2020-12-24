package com.enums;

import java.util.HashMap;
import java.util.Map;

public enum eFaceRecognition {

	  _FACE_TRACKING_START("FACE_TRACKING_START" , 0x00000001)		//	얼굴 추적 시작
	, _FACE_TRACKING("FACE_TRACKING" , 0x00000002 )					//	얼굴 추적 진행중
	, _FACE_TRACKING_END("FACE_TRACKING_END" , 0x00000004 )			//	얼굴 추적 종료
	, _FIND_REGISTED_FACE("FIND_REGISTED_FACE" , 0x00000010 )		//	등록된 얼굴이 인식됨
	, _FIND_UNREGISTED_FACE("FIND_UNREGISTED_FACE"  , 0x00000020 )	//	미 등록된 얼굴이 인식됨
	, _UPDATE_FACE_INFO("UPDATE_FACE_INFO" , 0x00000040)			//	얼굴 속성 업데이트 됨
	;
	
//	얼굴 검출/분석 상태		값 (16진수)	설명
//	얼굴 추적 시작			0x00000001	얼굴 검출& 추적 시작됨.
//	얼굴 추적 진행중			0x00000002	얼굴이 추적 중인 상태
//	얼굴 추적 종료			0x00000004	얼굴 추적이 종료된 상태
//	등록된 얼굴이 인식됨		0x00000010	
//	미 등록된 얼굴이 인식됨	0x00000020	
//	얼굴 속성 업데이트 됨		0x00000040	얼굴 속성 값인 나이, 성별이 업데이트 됨을 알림.

	   
	private final String value;
	private final int intValue;

	private final static Map<String, eFaceRecognition> map = new HashMap<String, eFaceRecognition>();
	static {
		for (eFaceRecognition str : values())
			map.put(str.getValue() , str);
	}

	private eFaceRecognition(String value, int intValue) {
		this.value = value;
		this.intValue = intValue;
	}

	public static eFaceRecognition forValue(String value) {
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