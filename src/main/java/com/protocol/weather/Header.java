package com.protocol.weather;

import lombok.Data;

@Data
public class Header {
	private String resultCode;
	private String resultMsg;
	
	public String toString()
	{
		return "ResultCode = " + resultCode + " | resultMsg = " + resultMsg;
		
	}

}
