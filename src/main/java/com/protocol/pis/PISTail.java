package com.protocol.pis;

import java.nio.ByteOrder;

import com.protocol.Message;

public class PISTail implements Message {

	final byte last = (byte)0xFA;
	
	@Override
	public byte[] array(ByteOrder order) {
		// TODO Auto-generated method stub
		return null;
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
		return 0;
	}

}
