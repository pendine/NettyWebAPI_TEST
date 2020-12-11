package com.protocol.client;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.protocol.Message;

public class ClientTail implements Message {
	
	
	ClientHeader clientHeader;
	ClientBody clientBody;
	byte crc;
	byte parityCode;
	
	public static int SIZE = 1;
	
	

	public ClientTail(ClientHeader clientHeader, ClientBody clientBody) {
		this.clientHeader = clientHeader;
		this.clientBody = clientBody;
		
		setParity(clientHeader.array(ByteOrder.BIG_ENDIAN), clientBody.array(ByteOrder.BIG_ENDIAN));
	}
	
	public ClientTail(ClientHeader clientHeader, ClientBody clientBody, ByteOrder byteOrder) {
		this.clientHeader = clientHeader;
		this.clientBody = clientBody;
		
		setParity(clientHeader.array(byteOrder), clientBody.array(byteOrder));
	}
	
	
	
	@Override
	public byte[] array(ByteOrder order) {
		// TODO Auto-generated method stub
//		ByteBuffer byteBuffer = ByteBuffer.allocate(this.parityCode);
		ByteBuffer byteBuffer = ByteBuffer.allocate( 1 );
		
		byteBuffer.order(order);
		
		byteBuffer.put(parityCode);
		
		return byteBuffer.array();
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
		// return total packet tail length
		return 0;
	}
	
	public void setParity(byte[] header, byte[] body) {
		byte checkSum = 0;
		for (byte b : header){
			checkSum ^= b;
			}
		if(body != null){
			for (byte b : body){
				checkSum ^= b;
				}
			}
		this.parityCode = checkSum;
	}
	
	public byte getParity(byte[] header, byte[] body) {
		byte checkSum = 0;
		for (byte b : header){
			checkSum ^= b;
			}
		if(body != null){
			for (byte b : body){
				checkSum ^= b;
				}
			}
		this.parityCode = checkSum;
		return this.parityCode;
	}
	
	public byte getParity() {
		return this.parityCode;
	}

}
