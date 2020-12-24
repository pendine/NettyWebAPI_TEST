package com.enums;

import java.util.HashMap;
import java.util.Map;

public enum eVehicleType {

	_UNKNOWN("UNKNOWN", 0x00000001)
	, _SEDAN("SEDAN", 0x00000002 )
	, _SUV("SUV", 0x00000004 )
	, _VAN("VAN", 0x00000008 )
	, _TRUCK("TRUCK", 0x00000010 )
	, _BUS("BUS", 0x00000020 )
	, _TWOWHEELER("TWOWHEELER", 0x00000040 )
	;
	
//	UNKNOWN   	0x00000001
//	SEDAN     	0x00000002
//	SUV       	0x00000004
//	VAN       	0x00000008
//	TRUCK     	0x00000010
//	BUS       	0x00000020
//	TWOWHEELER	0x00000040

	
	   
	private final String value;
	private final int intValue;

	private final static Map<String, eVehicleType> map = new HashMap<String, eVehicleType>();
	static {
		for (eVehicleType str : values())
			map.put(str.getValue() , str);
	}

	private eVehicleType(String value, int intValue) {
		this.value = value;
		this.intValue = intValue;
	}

	public static eVehicleType forValue(String value) {
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