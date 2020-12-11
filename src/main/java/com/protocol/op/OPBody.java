package com.protocol.op;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.protocol.Message;

public class OPBody implements Message {

	
	public final static int SIZE = 3; 
	
	public byte[] body;
	
	@Override
	public byte[] array(ByteOrder order) {
		// TODO Auto-generated method stub
		ByteBuffer byteBuf = ByteBuffer.wrap(this.body);
		byteBuf.order(order);
		
		return byteBuf.array();
	}

	@Override
	public void parse(byte[] array, ByteOrder order) {
		// TODO Auto-generated method stub
		ByteBuffer byteBuf = ByteBuffer.wrap(array);
		byteBuf.order(order);
		
		byteBuf.get(this.body, 0 , array.length);
	}

	@Override
	public void parse(byte[] array) {
		// TODO Auto-generated method stub
//		ByteBuffer byteBuf = ByteBuffer.wrap(this.body);
//		byteBuf.order(ByteOrder.BIG_ENDIAN);
//		
//		this.body = byteBuf.array();
		this.body = array;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
