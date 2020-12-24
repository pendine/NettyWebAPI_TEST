package com.protocol.op;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.protocol.Message;

public class OPHeader implements Message {
	
	//오퍼레이터에서 패킷을 어떻게 보내는지가 중요하기때문에 추후 변경이 이루어질수 있음.
	
	private byte[] commonhead = new byte[10];
	private byte dle; // 0x10 
	private byte stx; // 0x02
	
	private byte len;
	
	public static int SIZE = 12;	

	@Override
	public int size() {
		return this.SIZE;
	}

	public OPHeader() {	}
	
	public OPHeader(byte[] array, ByteOrder order) {
		parse(array, order);
	}

	public OPHeader(byte dle, byte stx , byte len ) {
		this.dle = dle;
		this.stx = stx;
		this.len = len;
	}
		
	@Override
	public byte[] array(ByteOrder order) {
		ByteBuffer buffer = ByteBuffer.allocate(this.size());
		buffer.order(order);
		
		buffer.put(this.commonhead);
		buffer.put(this.dle);		
		buffer.put(this.stx);
		buffer.put(this.len);
		
		return buffer.array();
	}

	@Override
	public void parse(byte[] array, ByteOrder order) {
		ByteBuffer buffer = ByteBuffer.wrap(array);		
		buffer.order(order);
		
		buffer.get(this.commonhead);
		
		this.dle = buffer.get();
		this.stx = buffer.get();
		this.len = buffer.get();
		
	}

	public byte getSender() {
		return this.dle;
	}
	public void setSender(byte sender) {
		this.dle = sender;
	}

	public byte getReceiver() {
		return this.stx;
	}
	public void setReceiver(byte receiver) {
		this.stx = receiver;
	}
	
	public byte getLen() {
		return this.len;
	}
	public void setLen(byte len) {
		this.len = len;
	}

	@Override
	public String toString() {
		return "OPHeader [sender=" + dle + ", receiver=" + stx + ", length= " + len + " ]";
	}

	@Override
	public void parse(byte[] array) {
		// TODO Auto-generated method stub
		
	}

}