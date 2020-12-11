package com.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public abstract class StringHelper {
	
	
	public static List<String> seperateString(String srcValue, String delimeter) {
		List<String> list = new ArrayList<String>();

		StringTokenizer stringTokenizer = new StringTokenizer(srcValue, delimeter);

		while(stringTokenizer.hasMoreTokens()) {
			list.add(stringTokenizer.nextToken().trim());
		}

		return list;
	}
	
	/**
	 * 문자열 str 길이가 len 길이보다 작다면 str 왼쪽에 pad 문자를 붙임  
	 * @param str : 대상 문자열
	 * @param len : 목표길이
	 * @param pad : padding 문자
	 * @return 전체 길이가 len인 문자열
	 * @author 한호연
	 */
    public static String lpad(String str, int len, String pad) {
        String result = str;
        int templen   = len - result.length();

        for (int i = 0; i < templen; i++){
              result = pad + result;
        }
        
        return result;
     }
}
