package com.protocol.client;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.protocol.Message;
import com.util.ByteToHex;

public class ClientBody implements Message {
	
	public static final int SIZE = 3;

	private byte[] bodyByte;
	
	private byte[] tailByte;
	
	private int tailSize=1;

	public ClientBody() {}
	
	public ClientBody(byte[] arr, int length) {
		for(int i =0; i< length; i++) {
			bodyByte[i] = arr[i];
		}
	}
	
	public ClientBody(byte[] arr) {
		bodyByte = arr;
	}
	
	public byte[] getTailByte() {
		return this.tailByte;
	}
	
	@Override
	public byte[] array(ByteOrder order) {
		// TODO Auto-generated method stub
		ByteBuffer buffer = ByteBuffer.allocate(this.bodyByte.length);
		buffer.order(order);
		
		//for문에서 사이즈를 확인하기 위해서 length를 지속적으로 호출한다고함.
		//지속적인 사이즈 호출을 막기위해서 크기를 변수를 선언,초기화함.
		for(int i = 0, size = bodyByte.length; i<size ; i ++) {
			buffer.put(bodyByte[i]);
		}
		
		//for문이 iterator보다 빠르다고 확인했음.
		//단위수가 십만이 넘어가지 않는 이상 무의미 할것같지만 일단 적용해봄
//		for(byte e:bodyByte) {  
//			buffer.put(e);
//		}
		
		return buffer.array();
	}

	@Override
	public void parse(byte[] array, ByteOrder order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void parse(byte[] array) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		//return total packet Body length
		return bodyByte.length;
	}

	public void setBody(byte[] readBytes, int start, byte length) {
		// TODO Auto-generated method stub
		for(int i=0, tmp = start; i<ByteToHex.byteToInt(length); i++) {
			bodyByte[i] = readBytes[i+tmp];
		}
		
	}
		
	public byte[] getBody() {
		// TODO Auto-generated method stub
		return bodyByte;
	}

}
