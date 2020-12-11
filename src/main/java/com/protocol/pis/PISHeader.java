package com.protocol.pis;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.protocol.Message;

public class PISHeader implements Message {
	
	byte stx1 = (byte) 0xF0;
	byte stx2 = (byte) 0xF1;
	byte stx3 = (byte) 0xF2;
	byte stx4 = (byte) 0xF3;
	
	public static int SIZE = 4;
	
	public PISHeader() {}
	
	public PISHeader(byte[] array, ByteOrder byteOrder) {
		// TODO Auto-generated constructor stub
		
		parse(array, byteOrder);
	}
	@Override
	public byte[] array(ByteOrder order) {
		// TODO Auto-generated method stub
		ByteBuffer byteBuf = ByteBuffer.allocate(this.size());
		
		byteBuf.put(stx1);
		byteBuf.put(stx2);
		byteBuf.put(stx3);
		byteBuf.put(stx4);
		
		return byteBuf.array();
	}
	@Override
	public void parse(byte[] array, ByteOrder order) {
		// TODO Auto-generated method stub
		
		ByteBuffer buffer = ByteBuffer.wrap(array);		
		buffer.order(order);
		buffer.get();
		
		
	}
	@Override
	public void parse(byte[] array) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.SIZE;
	}
	
	public byte[] getDefaultHeader() {
		return new byte[]{ stx1, stx2, stx3, stx4};
	}
	
}
