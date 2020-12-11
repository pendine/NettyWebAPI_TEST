package com.network.netty.codec;

import java.nio.ByteOrder;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.domain.PISTemplate;
import com.enums.eOPOpcode;
import com.protocol.client.ClientBody;
import com.protocol.client.ClientForm;
import com.protocol.client.ClientHeader;
import com.protocol.client.ClientTail;
import com.service.PISManager;
import com.util.ApplicationContextProvider;
import com.util.ByteToHex;
import com.util.NettyHelper;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PISTCPEncoder extends MessageToByteEncoder<Object> {

	private static final Logger logger = LoggerFactory.getLogger(PISTCPEncoder.class);
	PISManager pisManager = (PISManager) ApplicationContextProvider.getApplicationContext().getBean("pisManager");
	

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
		// TODO Auto-generated method stub
		ClientForm cf = (ClientForm) msg;
		String ip = NettyHelper.getRemoteAddress(ctx.channel());
		int port = NettyHelper.getRemotePort(ctx.channel());
		
		String pisID = pisManager.getPISTemplate(ctx.channel()).PIS_ID;
		int clientPacketLength = cf.array(ByteOrder.LITTLE_ENDIAN).length;

		logger.info(" PIS ID : {} | ip : {} | port : {} ", pisID, ip, port ); 
//		logger.info(" PIS ID : {} | encoder method msg Context(Little) : {}", pisID , ByteToHex.bytesToHex( cf.array(ByteOrder.LITTLE_ENDIAN) ) );
		logger.info(" PIS ID : {} | encoder method msg Context(Big) : {}", pisID , ByteToHex.bytesToHex( cf.array(ByteOrder.BIG_ENDIAN) ) );
		
		out = ctx.alloc().buffer(clientPacketLength);
		out.writeBytes(cf.array(ByteOrder.LITTLE_ENDIAN));
		ctx.writeAndFlush(out);
		
		/*
		byte[] message = (byte[]) msg;
		
		char commandValue = (char) ( (0x00 <<4 ) | 0x00 );
		ClientForm clientForm = new ClientForm();
		clientForm.setOpcode( (byte) eOPOpcode._packetRequestCommand.getValue() , commandValue);
		
		out = ctx.alloc().buffer( ClientHeader.SIZE + ClientBody.SIZE + ClientTail.SIZE );
		out.writeBytes(clientForm.array(ByteOrder.LITTLE_ENDIAN));
		ctx.channel().writeAndFlush(out);
		*/
//		ctx.writeAndFlush(out);
		
	}
	
}
