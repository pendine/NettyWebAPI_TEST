package com.network.netty.codec;

import java.nio.ByteOrder;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.domain.PISTemplate;
import com.network.netty.NettyTCPClient;
import com.util.ApplicationContextProvider;
import com.util.ByteDLE;
import com.util.ByteToHex;
import com.util.DleStuffingData;
import com.util.NettyHelper;
import com.util.TypeHelper;
import com.vo.voPRLT_CTLR_STTS;
import com.protocol.op.OPBody;
import com.protocol.op.OPForm;
import com.protocol.op.OPHeader;
import com.protocol.pis.PISForm;
import com.protocol.pis.PISHeader;
import com.service.PISManager;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class OPDecoder extends ByteToMessageDecoder {

	private static final Logger logger = LoggerFactory.getLogger(OPDecoder.class);

	PISManager pisManager = (PISManager) ApplicationContextProvider.getApplicationContext().getBean("pisManager");
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {

		MDC.put("OP", "Operation Terminal");
		// TODO Auto-generated method stub				
		if( 4 > byteBuf.readableBytes() ) {
			logger.debug("D | Must be accepted more data(protocol), length[{}] > readableBytes[{}]", 4 , byteBuf.readableBytes());
			return;
		}
		
		logger.info("op packet decoding");

		while(byteBuf.readableBytes() >= 4) { // Header Read
			byte[] getByte = new byte[ byteBuf.readableBytes()];
			byteBuf.readBytes(getByte);
			
			byte[] headerTmp = new byte[OPHeader.SIZE];
			System.arraycopy(getByte , 0 , headerTmp , 0 , headerTmp.length );
			
			byte[] bodyTmp = new byte[getByte.length - headerTmp.length];
			
			for(int i = headerTmp.length; i<getByte.length; i++) {
				bodyTmp[i - headerTmp.length] = getByte[i];
			}
			
			System.out.println(" bodyTmp : " + ByteToHex.bytesToHex(bodyTmp));
			
//			bodyTmp = ByteDLE.dleStuffingProcess(bodyTmp);
			
			OPHeader h = new OPHeader(headerTmp, ByteOrder.BIG_ENDIAN);
			
//			System.out.println(" OPHeader : " + ByteToHex.bytesToHex(h.array(ByteOrder.BIG_ENDIAN)));
//			System.out.println(" bodyTmp : " + ByteToHex.bytesToHex(bodyTmp));

			OPForm f = new OPForm(h,bodyTmp);
			
//			f.body = bodyTmp;
			logger.info(" OPFORM ARRAY : {}" + ByteToHex.bytesToHex(f.array(ByteOrder.LITTLE_ENDIAN)) );
			logger.info(" opform opcode : {}"  + ByteToHex.byteToHex(f.getHeader().getOpCode()) );
			logger.info(" opform command val : {}" + ByteToHex.shortToHex(f.getCommandVal()) );
			out.add(f);
			
		}
		
		MDC.remove("OP");
		
	}
}
