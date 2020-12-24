package com.network.netty.codec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class OPEncoder extends MessageToByteEncoder<Object> {

	private static final Logger logger = LoggerFactory.getLogger(OPEncoder.class);

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
		// TODO Auto-generated method stub
		/*
		 * 운영단말과 통신을 하지 않으니 필요가 없음.
		 * 
		 * 
		ClientForm cf = (ClientForm) msg;
		String pisID = pisManager.getPISTemplate(ctx.channel()).PIS_ID;
		
		
		int clientPacketLength = cf.array(ByteOrder.LITTLE_ENDIAN).length;
		
		logger.info("Target PIS : {} | msg Context : {} " , pisID , ByteToHex.bytesToHex(cf.array(ByteOrder.LITTLE_ENDIAN)) );
		
		
		out = ctx.alloc().buffer(clientPacketLength);
		out.writeBytes(cf.array(ByteOrder.LITTLE_ENDIAN));
		
		ctx.writeAndFlush(out);
		
		*/
	}
	
}
