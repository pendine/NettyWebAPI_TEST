package com.protocol.op;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.protocol.Message;
import com.util.ByteDLE;
import com.util.ByteToHex;


public class OPForm implements Message {
	
	private static final Logger logger = LoggerFactory.getLogger(OPForm.class);
	
	//reset이나 원하는 동작을 보낼 타겟 pis의 정보가 헤더 혹은 바디에 있는지 확인하고 보내야함
	
	public OPHeader opHeader;
	public OPTail opTail;
	
	byte dle = (byte) 0x10; 
	
	public byte[] body;
	
	short commandVal;
	
	
	public final static int SIZE = OPHeader.SIZE + OPBody.SIZE;
	
	public OPForm() {}
	
	public OPForm(OPHeader opH, byte[] body) {
		this.opHeader = opH;		
		byte[] bodyTmp = ByteDLE.dleStuffingProcess(body);

		parse(body,ByteOrder.LITTLE_ENDIAN);
	}
	
	
	public OPHeader getHeader() {
		return this.opHeader;
	}

	public byte[] getBody() {
		return this.body;
	}
	
	public short getCommandVal() {
		return this.commandVal;
	}
	
	@Override
	public byte[] array(ByteOrder order) {
		// TODO Auto-generated method stub
		ByteBuffer byteBuffer = ByteBuffer.allocate( (int)this.opHeader.getLen() -  OPHeader.SIZE );
		byteBuffer.order(order);
		
		byteBuffer.put( this.opHeader.array(order) );
		
		return byteBuffer.array();

	}

	@Override
	public void parse(byte[] array, ByteOrder order) {
		// TODO Auto-generated method stub
		ByteBuffer bytebuf = ByteBuffer.wrap(array);
		bytebuf.order(order);
		
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
