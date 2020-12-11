package com.util;

public class OPCrc {
	public static short Crc16_Make(byte[] rdata, int len) {
	      int i, n;
	      int wCrc = 0xFFFF;
	      int wCh;
	      
	      for(i=0; i<len; i++) {
	         wCh = rdata[i];
	         
	         for(n=0; n<8; n++) {
	            if(((wCh^wCrc) & 0x0001) != 0) 
	               wCrc = (wCrc >> 1) ^ 0xA001;
	            else
	               wCrc = wCrc >> 1;
	            
	            wCh = wCh >> 1;
	         }
	      }
	      return (short)wCrc;
	   }
}
