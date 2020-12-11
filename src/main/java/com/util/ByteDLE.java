package com.util;

import java.util.List;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

public class ByteDLE {

	private static byte dle = 0x10;
	static List<Byte> array;
	
	public static byte[] dleStuffingProcess(byte[] aaa) {
		array = new ArrayList<Byte>();
		
		for(int i=0; i<aaa.length-4; i++) {
			if( (aaa[i] == dle) && (aaa[i+1] == dle) ) 
			{
				array.add(aaa[i]);
				i = i + 1;
				continue;
			}
			else
			{
				array.add(aaa[i]);
			}
		}
		
		byte[] returnArr = new byte[array.size()];
		
		for(int i =0; i<returnArr.length; i++) {
			returnArr[i] = array.get(i);
		}
		
		array = null;
		
		return returnArr;
	}
	
	public static byte[] dleStuffingProcess2(byte[] aaa) {
		array = new ArrayList<Byte>();
		
//		array.add(aaa[0]);
		
		for(int i=0; i<aaa.length-1; i++) {
			
			if( (aaa[i] == dle) && (aaa[i+1] == dle) ) 
			{
				System.out.println("같다");
				array.add(aaa[i]);
				i = i + 1;
				continue;
			}
			else if((aaa[i] != dle) || (aaa[i+1] != dle) )
			{
				array.add(aaa[i]);
			}
			
			if ( i == aaa.length-2)
			{
				array.add(aaa[i+1]);
			}
		}
		
		byte[] returnArr = new byte[array.size()];
		
		for(int i =0; i<returnArr.length; i++) {
			returnArr[i] = array.get(i);
		}
		
		array = null;
		
		return returnArr;
	}
	
	public static byte[] dleStuffingProcess(byte[] aaa, ByteOrder order) {
		array = new ArrayList<Byte>();
		
		ByteBuffer byteBuf = ByteBuffer.wrap(aaa);
		byteBuf.order(order);
		
		for(int i=1; i<aaa.length; i++) {
			if(aaa[i-1] == dle && aaa[i] ==dle) {
				array.add(aaa[i]);
				aaa[i] = 0x00;
			}
		}
		
		byte[] returnArr = new byte[array.size()];
		
		for(int i =0; i<returnArr.length; i++) {
			returnArr[i] = array.get(i);
		}

		array = null;
		
		return returnArr;
	}
}
