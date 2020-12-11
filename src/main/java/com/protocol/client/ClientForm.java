package com.protocol.client;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.enums.eOPOpcode;
import com.enums.ePISOpcode;
import com.google.common.primitives.UnsignedInteger;
import com.protocol.Message;
import com.util.ByteToHex;

import io.netty.buffer.ByteBuf;

public class ClientForm implements Message {
	ClientHeader clientHeader;
	byte[] body;
	
	public ClientForm() {}
	
	public ClientHeader getClientHeader() {
		return this.clientHeader;
	}
	
	public void setClientHeader(ClientHeader clientHeader) {
		this.clientHeader = clientHeader;
	}
	public void setClientHeader(byte[] bytes) {
	}
	
	public byte[] getBodyBytes() {
		return this.body;
	}

	@Override
	public byte[] array(ByteOrder order) {
		ByteBuffer buffer = ByteBuffer.allocate(0);
		buffer.order(order);
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
		return 0;
	}
	
	public String toString() {
		return " clientHeader : "+  this.clientHeader.toString()
		;
	}

}
