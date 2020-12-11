package com.network.netty.codec;

import java.nio.ByteOrder;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.domain.PISTemplate;
import com.protocol.client.ClientForm;
import com.protocol.pis.PISForm;
import com.service.PISManager;
import com.util.ApplicationContextProvider;
import com.util.ByteToHex;
import com.util.NettyHelper;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class OPEncoder extends MessageToByteEncoder<Object> {

	private static final Logger logger = LoggerFactory.getLogger(OPEncoder.class);
	PISManager pisManager = (PISManager) ApplicationContextProvider.getApplicationContext().getBean("pisManager");

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
