package com.enums;

import java.util.HashMap;
import java.util.Map;

import com.util.TypeHelper;

public enum eCategory {
	_POP("POP", "강수확률"),
	_PTY("PTY", "강수형태"),
	_REH("REH", "습도"),
	_SKY("SKY", "하늘상태"),
	_T3H("T3H", "3시간 기온"),
	_TMX("TMX", "낮 최고기온"),
	_UUU("UUU", "풍속(동서성분)"),
	_VEC("VEC", "풍향"),
	_VVV("VVV", "풍속(남북성분)"),
	_WSD("WSD", "풍속"),
	
	;
	   
	private final String value;
	private final String strValue;

	private final static Map<String, eCategory> map = new HashMap<String, eCategory>();
	static {
		for (eCategory str : values())
			map.put(str.getValue() , str);
	}

	private eCategory(String value, String strValue) {
		this.value = value;
		this.strValue = strValue;
	}

	public static eCategory forValue(String value) {
		return map.get(value);
	}
	
	public String getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return this.strValue;
	}
}
