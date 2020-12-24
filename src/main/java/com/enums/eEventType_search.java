package com.enums;

import java.util.HashMap;
import java.util.Map;

public enum eEventType_search {
	  _PROWL("PROWL" , 0x1)									//	배회	
	, _PATH_PASSING("PATH_PASSING" ,  0x2 )					//	경로 통과
	, _DIRECTIONAL_MOVEMENT("DIRECTIONAL_MOVEMENT"  ,  0x4 )//	방향성 이동	
	, _ENTER("ENTER"  , 0x8 )								//	진입
	, _ADVANCEDMENT("ADVANCEDMENT"  , 0x10 )				//	진출	
	, _STOP("STOP"  , 0x20)									//	멈춤	
	, _ABANDONED("ABANDONED"  , 0x40 )						//	버려짐
	, _CROSSING_BORDER("CROSSING_BORDER", 0x80 )			//	경계선 통과	
	, _SMOKE("SMOKE", 0x100 )								//	연기
	, _FIRE("FIRE", 0x200 )									//	불꽃	
	, _COLLAPSE("COLLAPSE", 0x400)							//	쓰러짐
	, _CLUSTER("CLUSTER", 0x800 )							//	군집
	, _VIOLENCE("VIOLENCE", 0x1000 )						//	폭력
	, _MULTI_CROSSING_BORDER("MULTI_CROSSING_BORDER", 0x2000 )	//	멀티 경계선 통과
	, _CAR_ACCIDENT("CAR_ACCIDENT", 0x4000 )				//	차량 사고
	, _VEHICLE_STOP("VEHICLE_STOP", 0x8000)					//	차량 멈춤
	, _TRAFFIC_TIE_UP("TRAFFIC_TIE_UP", 0x10000 )			//	교통 정체
	, _COLOR_CHANGE("COLOR_CHANGE", 0x20000 )				//	색상 변화
	, _VEHICLE_PARKING("VEHICLE_PARKING", 0x40000 )			//	차량 주차
	, _ELIMINATE("ELIMINATE", 0x80000 )						//	제거됨
	, _DANGEROUS_WATER_LEVEL("DANGEROUS_WATER_LEVEL", 0x100000)	//	위험수위
	, _AREA_COLOR("AREA_COLOR", 0x200000 )					//	영역색상
	, _STAY("STAY", 0x400000 )								//	체류
	, _STAY_EXCEEDING_PERSONNEL("STAY_EXCEEDING_PERSONNEL", 0x800000 )	//	체류-초과인원
	, _STAY_TIME_EXCEEDED("STAY_TIME_EXCEEDED", 0x1000000 )				//	체류-시간초과
	, _STAY_SINGLE_POPULATION_STAY("STAY_SINGLE_POPULATION_STAY", 0x2000000 )	//	체류-단독인원체류
	;
	
//	이름				실시간 이벤트값		검색용 값
//	이벤트 종류			값				플래그 형태 표현
//	배회				0				0x1
//	경로 통과			1				0x2
//	방향성 이동			2				0x4
//	진입				3				0x8
//	진출				4				0x10
//	멈춤				5				0x20
//	버려짐			6				0x40
//	경계선 통과			7				0x80
//	연기				8				0x100
//	불꽃				9				0x200
//	쓰러짐			10				0x400
//	군집				11				0x800
//	폭력				12				0x1000
//	멀티 경계선 통과		13				0x2000
//	차량 사고			14				0x4000
//	차량 멈춤			15				0x8000
//	교통 정체			16				0x10000
//	색상 변화			17				0x20000
//	차량 주차			18				0x40000
//	제거됨			19				0x80000
//	위험수위			20				0x100000
//	영역색상			21				0x200000
//	체류				22				0x400000
//	체류-초과인원		23				0x800000
//	체류-시간초과		24				0x1000000
//	체류-단독인원체류	25				0x2000000


	
	   
	private final String value;
	private final int intValue;

	private final static Map<String, eEventType_search> map = new HashMap<String, eEventType_search>();
	static {
		for (eEventType_search str : values())
			map.put(str.getValue() , str);
	}

	private eEventType_search(String value, int intValue) {
		this.value = value;
		this.intValue = intValue;
	}

	public static eEventType_search forValue(String value) {
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