package com.protocol.op;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.protocol.Message;

public class OPTail implements Message {

	public byte dle = (byte) 0x10;
	public byte etx = (byte) 0x03;
	public short crc;
	
	public static int SIZE = 4; 
	
	public OPTail() {
		
	}
	
	public OPTail(byte[] array, ByteOrder order) {
		parse(array, order);
	}
	
	
	@Override
	public byte[] array(ByteOrder order) {
		// TODO Auto-generated method stub
		ByteBuffer byteBuf = ByteBuffer.wrap( new byte[] {dle,etx} );
		byteBuf.order(ByteOrder.BIG_ENDIAN);
		
		return byteBuf.array();
	}

	@Override
	public void parse(byte[] array, ByteOrder order) {
		// TODO Auto-generated method stub
		ByteBuffer byteBuf = ByteBuffer.wrap( array );
		byteBuf.order(order);
		
		this.dle = byteBuf.get();
		this.etx = byteBuf.get();
		this.crc = byteBuf.getShort();
	}

	@Override
	public void parse(byte[] array) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
