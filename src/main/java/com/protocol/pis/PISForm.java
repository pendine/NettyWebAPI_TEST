package com.protocol.pis;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.primitives.UnsignedInteger;
import com.protocol.Message;
import com.util.ByteToHex;
import com.util.TypeHelper;

public class PISForm implements Message {
	
	private static final Logger logger = LoggerFactory.getLogger(PISForm.class);
	
	PISHeader pisHeader;
	PISBody pisBody;
	PISTail pisTail;
	
	public byte[] body;

	public byte index;
	public short emptySlot;
	public int inCountTmp;
	public int outCountTmp;
	public UnsignedInteger inCount;
	public UnsignedInteger outCount;
	public char EventTrigger;
	public byte[] commandResponse = new byte[3];
	public byte reserved;
	public short vehicleCount;
	public byte cs;
	public byte end1;
	public byte end2;
	
	public static int SIZE = 26;
	
	public PISForm() {}
	
	
	public PISForm(PISHeader pisH, byte[] body) {
		
		this.pisHeader = pisH;
		if(body != null && body.length == PISBody.SIZE){
			this.body = body.clone();
			parse(this.body, ByteOrder.LITTLE_ENDIAN);
//			parse(this.body, ByteOrder.BIG_ENDIAN);
		}else {
//			System.out.println("not matched pis packet");
			logger.info("not matched pis packet");
			return;
		}
		
	}	
	
	public PISForm(PISHeader pisH, PISBody pisB) {
		pisHeader = pisH;
		pisBody = pisB;
	}

	public PISForm(PISHeader pisH, PISBody pisB, PISTail pisT ) {
		pisHeader = pisH;
		pisBody = pisB;
		pisTail = pisT;
	}

	@Override
	public byte[] array(ByteOrder order) {
		// TODO Auto-generated method stub
//		ByteBuffer buffer = ByteBuffer.allocate(this.SIZE);
		ByteBuffer buffer = ByteBuffer.allocate(pisBody.SIZE);
		
		buffer.order(order);
		
//		buffer.put(this.pisHeader.array(order));
		buffer.put(this.index);
		buffer.putShort(this.emptySlot);
		buffer.putInt(this.inCount.intValue());
		buffer.putInt(this.outCount.intValue());
		buffer.putChar(this.EventTrigger);
		buffer.put(this.commandResponse);
		buffer.put(this.reserved);
		buffer.putShort(this.vehicleCount);
		buffer.put(this.cs);
		buffer.put(this.end1);
		buffer.put(this.end2);
		
		return buffer.array();
	}


	@Override
	public void parse(byte[] array, ByteOrder order) {
		// TODO Auto-generated method stub
		ByteBuffer byteBuffer = ByteBuffer.wrap(array); 
		byteBuffer.order(order);
		
		this.index = byteBuffer.get();
		this.emptySlot = byteBuffer.getShort();
		this.inCountTmp = byteBuffer.getInt();
		this.outCountTmp = byteBuffer.getInt();
		this.EventTrigger = byteBuffer.getChar();
		byteBuffer.get(this.commandResponse);
		this.reserved = byteBuffer.get();
		this.vehicleCount = byteBuffer.getShort();
		this.cs = byteBuffer.get();
		this.end1 = byteBuffer.get();
		this.end2 = byteBuffer.get();
		
		this.inCount = UnsignedInteger.fromIntBits( inCountTmp );
		this.outCount = UnsignedInteger.fromIntBits( outCountTmp );

	}
	
	
	public byte[] returnBytes(UnsignedInteger e) {
		int aaa = e.intValue();
		byte one = (byte) ((byte)(aaa ) &  0xff);
		byte two = (byte) ((byte)(aaa >> 1) &  0xff);
		byte three = (byte) ((byte)(aaa >> 2) &  0xff);
		byte four = (byte) ((byte)(aaa >> 3) &  0xff);
		
		return new byte[] {four, three, two, one};
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

	public PISHeader getHeader() {
		return pisHeader;
	}

	public void setHeader(PISHeader header) {
		this.pisHeader = header;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}

	
	public byte calculatedCS() {
		byte a = (byte)0x00;
		for(int i=0; i<pisBody.SIZE-3; i++) {
			a += body[i];
		}
		return a;
	}
	
	public byte getCS() {
		return this.cs;
	}
	
	

}
