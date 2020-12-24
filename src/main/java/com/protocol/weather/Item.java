package com.protocol.weather;

import java.lang.reflect.Field;

import lombok.Data;

@Data
public class Item {
	String baseDate;
	String baseTime;
	String category;
	String fcstDate;
	String fcstTime;
	String fcstValue;
	int nx;
	int ny;
	
	public Item(){}
	
	public Item(String defaultStr) {
//		ex) 
//		"baseDate":"20201217","baseTime":"1100","category":"POP"
//		,"fcstDate":"20201217","fcstTime":"1500","fcstValue":"10","nx":55,"ny":127
//		
//		int start = defaultStr.indexOf("\""); 
//		int end = defaultStr.indexOf("\"",start+1);
//		if(defaultStr.substring(start+1, end).equals("baseDate")) {
//			start = defaultStr.indexOf("\"",end+1);
//			end = defaultStr.indexOf("\"",start+1);
//			this.baseDate = defaultStr.substring(start+1, end);
//		}
//	
//		start = defaultStr.indexOf("\"",end+1);
//		end = defaultStr.indexOf("\"",start+1);
//		if(defaultStr.substring(start+1, end).equals("baseDate")) {
//			start = defaultStr.indexOf("\"",end+1);
//			end = defaultStr.indexOf("\"",start+1);
//			this.baseDate = defaultStr.substring(start+1, end);
//		}
		
		this.baseDate = findStringValue("\"", defaultStr, "baseDate");
		this.baseTime = findStringValue("\"", defaultStr, "baseTime");
		this.category = findStringValue("\"", defaultStr, "category");
		this.fcstDate = findStringValue("\"", defaultStr, "fcstDate");
		this.fcstTime = findStringValue("\"", defaultStr, "fcstTime");
		this.fcstValue = findStringValue("\"", defaultStr, "fcstValue");
		this.nx = findIntValue(defaultStr, "nx");
		this.ny = findIntValue(defaultStr, "ny");
		
//		System.out.println(toString());
		
	}
	
	
	/** 
	 * 주어진 모든문자열
	 * 자를때 기준인 문자
	 * 타겟 문자열
	 *  
	 * @param seperate
	 * @param totalStr
	 * @param target
	 * @return
	 */
	public String findStringValue( String seperate, String totalStr, String target) {
		int start = totalStr.indexOf("\"", totalStr.indexOf(target)+ target.length() + 1); 
		int end	  = totalStr.indexOf("\"",start+1);
		
		return totalStr.substring(start+1, end);
		
	}

	public int findIntValue( String totalStr, String target) {
		int start = totalStr.indexOf(":", totalStr.indexOf(target)+ target.length() + 1); 
		int end	  = totalStr.indexOf(",",start+1);
		if(end < 0) {
			end	  = totalStr.length()-1;
		}
		
		int result = Integer.parseInt( totalStr.substring(start+1, end) );
		return result;
		
	}


	
	
	public String toString() {
		return " BaseDate : " + baseDate
				+" | baseTime : " + baseTime
				+" | category : " + category
				+" | fcstDate : " + fcstDate
				+" | fcstTime : " + fcstTime
				+" | fcstValue : " + fcstValue
				+" | nx : " + nx
				+" | ny : " + ny;
	}

}
