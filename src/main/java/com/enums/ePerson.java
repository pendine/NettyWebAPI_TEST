package com.enums;

import java.util.HashMap;
import java.util.Map;

public enum ePerson {
	
	_MALE("MALE", 0X00000001)
	, _CHILD("CHILD", 0X00000002)
	, _BAG("BAG", 0X00000004)
	, _STRIPE("STRIPE", 0X00000008)
	, _SUIT("SUIT", 0X00000010)
	, _TSHIRT("TSHIRT", 0X00000020)
	, _SHORT_SLEEVES("SHORT_SLEEVES", 0X00000040)
	, _LONG_TROUSERS("LONG_TROUSERS", 0X00000080)
	, _JEANS("JEANS", 0X00000100)
	, _SKIRT("SKIRT", 0X00000200)
	, _WHITE_CANE("WHITE_CANE", 0X00000400)
	;
	
//	MALE         	0x00000001	값이 OFF이면 여성
//	CHILD        	0x00000002	값이 OFF이면 어른
//	BAG          	0x00000004	
//	STRIPE       	0x00000008	
//	SUIT         	0x00000010	
//	TSHIRT       	0x00000020	
//	SHORT_SLEEVES	0x00000040	값이 Off이면 긴 소매
//	LONG_TROUSERS	0x00000080	
//	JEANS        	0x00000100	
//	SKIRT        	0x00000200	
//	WHITE_CANE   	0x00000400	

	   
	private final String value;
	private final int intValue;

	private final static Map<String, ePerson> map = new HashMap<String, ePerson>();
	static {
		for (ePerson str : values())
			map.put(str.getValue() , str);
	}

	private ePerson(String value, int intValue) {
		this.value = value;
		this.intValue = intValue;
	}

	public static ePerson forValue(String value) {
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