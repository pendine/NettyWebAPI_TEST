package com.domain;

import lombok.Data;

@Data
public class Header {
	private String resultcode;
	private String resultMsg;
	
	public String toString()
	{
		return "ResultCode = " + resultcode + " | resultMsg = " + resultMsg;
		
	}

}
