package com.enums;

import java.util.HashMap;
import java.util.Map;

import com.util.TypeHelper;

public enum ePISOpcode {
	_default(0x00, "_default"),
	_resetCommand(0x10, "_resetCommand"),
	_controllerVersionCheckCommand(0x11, "_controllerVersionCheckCommand"),
	_packetRequestCommand(0x12, "_packetRequestCommand"),
	_loadRomCommand(0x13, "_loadRomCommand"),
	_saveRomCommand(0x14, "_saveRomCommand"),
	_resetRomCommand(0x15, "_resetRomCommand"),
	_selfTestCommand(0x16, "_selfTestCommand"),
	_offsetCommand(0x20, "_offsetCommand"),
	_inCountControllCommand(0x22, "_inCountControllCommand"),
	_outCountControllCommand(0x23, "_outCountControllCommand")
	;
	   
	private final int value;
	private final String strValue;

	private final static Map<Integer, ePISOpcode> map = new HashMap<Integer, ePISOpcode>();
	static {
		for (ePISOpcode e : values())
			map.put(e.value, e);
	}

	private ePISOpcode(int value, String strValue) {
		this.value = value;
		this.strValue = strValue;
	}

	public static ePISOpcode forValue(int value) {
		return map.get(value);
	}

	public static ePISOpcode forValue(byte value) {
		return map.get(TypeHelper.unsignedByteToInt(value));
	}
	
	//문자열로 byte를 뽑아내야함.
//	public byte getByte(String value) {
//		map.get(key).get
//	}
	
	public int getValue() {
		return this.value;
	}
	
	public byte[] getByteArrayValue() {
		byte[] b = new byte[1];
		b[0] = (byte)this.value;
		return b;
	}

	@Override
	public String toString() {
		return this.strValue;
	}
	
	
}

