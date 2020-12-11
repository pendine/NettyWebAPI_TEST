package com.util;

public class ByteToHex {
	
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	public static byte hexToByteArray(String s) {	
	    return (byte) ((Character.digit(s.charAt(0), 16) << 4)
                + Character.digit(s.charAt(1), 16));
	}
	
	private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
	
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for (int j = 0; j < bytes.length; j++) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = HEX_ARRAY[v >>> 4];
	        hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	public static String byteToHex(byte bytes) {
	    int v = bytes & 0xFF;
	    char b = HEX_ARRAY[v>>>4];
	    char f = HEX_ARRAY[v&0x0f];
	    char[] hexChars = {b,f};
	    
	    return new String(hexChars);
	}
	
	public static String stringToHex(String s) {
	    String result = "";

	    for (int i = 0; i < s.length(); i++) {
	      result += String.format("%02X ", (int) s.charAt(i));
	    }
	    return result;
	}
	
	public static String shortToHex(short c) {
		byte ten = (byte) ((c >> 8) & 0xff);
		byte one = (byte) (c & 0xff); 
		
		StringBuilder result = new StringBuilder();
		
		result.append(ByteToHex.byteToHex(ten));
		result.append(ByteToHex.byteToHex(one));
		
	    return result.toString();
	}
	
	
	public static String stringToHex(char c) {
		String s = Character.toString(c);
	    String result = "";

	    for (int i = 0; i < s.length(); i++) {
	      result += String.format("%02X ", (int) s.charAt(i));
	    }
	    return result;
	}
	
	public static String byteToString(byte a) {
		char c = (char)a;
		return String.valueOf(c);
	}
	
	public static String bytesToString(byte[] a) {
		return new String( a );
	}
	

	public static int hexToInt(String hexbyte) {
		StringBuffer sb = new StringBuffer(hexbyte.length() * 2);
		String hexaDecimal;

		/* Hex byte[] to  Hex String */
		for(int x = 0; x < hexbyte.length(); x++)
		{
			hexaDecimal = "0" + Integer.toHexString(0xff & hexbyte.charAt(x) );
			sb.append( hexaDecimal.substring(hexaDecimal.length()-2));
		}
		/* Hex String  to   Decimal int */
		int decimal = Integer.parseInt(sb.toString(),16);	
		return decimal;
	}
	
	public static int byteToInt(byte bit) {
		int returnVal = (int) (0xff & bit);
		return returnVal;
	}
	
	public static byte[] intToByte(int integer) {
		byte[] byteArray = new byte[4];
		
		byteArray[0] = (byte)(integer >> 24 & 0xff);	// 3 byte move
		byteArray[1] = (byte)(integer >> 16 & 0xff);	// 2 byte move
		byteArray[2] = (byte)(integer >> 8 & 0xff);		// 1 byte move
		byteArray[3] = (byte)(integer & 0xff); 			// byte not move
		
		return byteArray;
	}
	
	public static String intToHex(int integer) {

		return bytesToHex( intToByte(integer) );
	}
	
	public static int bytesToInt(byte[] bytes) {
		byte[] aaa = new byte[4];
		int s1=0;
        int s2=0;
        int s3=0;
        int s4=0;
		if(bytes.length == 4) {
			for(int i=0; i < aaa.length; i++) {
				if(bytes.length - 1 - i >=0) {
				aaa [aaa.length-1 - i] = bytes[bytes.length - 1 - i];
				}else {
					aaa [aaa.length-1 - i] = 0;
				}

		         s1 = aaa[0] & 0xFF;
		         s2 = aaa[1] & 0xFF;
		         s3 = aaa[2] & 0xFF;
		         s4 = aaa[3] & 0xFF;
		    
			}
		}else if (bytes.length == 2) {
			 s1 = 0;
			 s2 = 0;
	         s3 = bytes[0] & 0xFF;
	         s4 = bytes[1] & 0xFF;
		}
				
        return ((s1 << 24) + (s2 << 16) + (s3 << 8) + (s4 << 0));
	}
	
	public static int bytesToOffset(byte[] bytes) {
		byte[] aaa = new byte[4];
		int s1=0;
        int s2=0;
        int s3=0;
        int s4=0;
		if(bytes.length == 4) {
			for(int i=0; i < aaa.length; i++) {
				if(bytes.length - 1 - i >=0) {
				aaa [aaa.length-1 - i] = bytes[bytes.length - 1 - i];
				}else {
					aaa [aaa.length-1 - i] = 0;
				}

		         s1 = aaa[0] & 0xFF;
		         s2 = aaa[1] & 0xFF;
		         s3 = aaa[2] & 0xFF;
		         s4 = aaa[3] & 0xFF;
		    
			}
		}else if (bytes.length == 2) {
			 s1 = 0;
			 s2 = 0;
	         s3 = bytes[1] & 0xFF;
	         s4 = bytes[0] & 0xFF;
		}
				
        return ((s1 << 24) + (s2 << 16) + (s3 << 8) + (s4 << 0));
	}
	
	public static int charToInt(char bytes) {
		return (bytes & 0xffffffff);			
	}
	
	
	
	
}
