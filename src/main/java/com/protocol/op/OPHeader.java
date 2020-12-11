package com.protocol.op;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.protocol.Message;

public class OPHeader implements Message {
	
	private byte[] commonhead = new byte[10];
	private byte dle; // 0x10 
	private byte stx; // 0x02
	private int addr; // -> TB_PRLT_CTLR.TB_PRLT_CTLR
	private byte opcode; 
	
	public static int SIZE = 17;	

	@Override
	public int size() {
		return this.SIZE;
	}

	public OPHeader() {	}
	
	public OPHeader(byte[] array, ByteOrder order) {
		parse(array, order);
	}

	public OPHeader(byte dle, byte stx, int addr, byte opCode) {
		this.dle = dle;
		this.stx = stx;
		this.addr = addr;
		this.opcode = opCode;
	}
		
	@Override
	public byte[] array(ByteOrder order) {
		ByteBuffer buffer = ByteBuffer.allocate(this.size());
		buffer.order(order);
		
		buffer.put(this.commonhead);
		buffer.put(this.dle);		
		buffer.put(this.stx);
		buffer.putInt(this.addr);
		buffer.put(this.opcode);
		
		return buffer.array();
	}

	@Override
	public void parse(byte[] array, ByteOrder order) {
		ByteBuffer buffer = ByteBuffer.wrap(array);		
		buffer.order(order);
		buffer.get(this.commonhead);
		this.dle = buffer.get();
		this.stx = buffer.get();
		this.addr = buffer.getInt();		
		this.opcode = buffer.get();
		
//		buffer.get(this.dle );
//		buffer.get(this.stx );
//		buffer.getInt(this.addr );		
//		buffer.get(this.opcode );
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

	public byte getOpCode() {
		return this.opcode;
	}

	public void setOpCode(byte opCode) {
		this.opcode = opCode;
	}

	public int getAddr() {
		return this.addr;
	}

	public void setAddr(int length) {
		this.addr = length;
	}

	@Override
	public String toString() {
		return "OPHeader [sender=" + dle + ", receiver=" + stx + ", opCode=" + opcode + ", length=" + addr + "]";
	}

	@Override
	public void parse(byte[] array) {
		// TODO Auto-generated method stub
		
	}

}