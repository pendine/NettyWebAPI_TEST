package com.network.netty.codec;

import java.nio.ByteOrder;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.protocol.pis.PISBody;
import com.protocol.pis.PISForm;
import com.protocol.pis.PISHeader;
import com.service.PISManager;
import com.util.ApplicationContextProvider;
import com.util.NettyHelper;
import com.util.TypeHelper;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class PISTCPDecoder extends ByteToMessageDecoder {

	private static final Logger logger = LoggerFactory.getLogger(PISTCPDecoder.class);
	
	private PISManager pisManager = (PISManager) ApplicationContextProvider.getApplicationContext().getBean(PISManager.class);
	
	
	int length = PISForm.SIZE;
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
		// TODO Auto-generated method stub	
		String ip = NettyHelper.getRemoteAddress(ctx.channel());
		String pisID = pisManager.getPISCtlrNmbrByIp( ip );
		
		MDC.put("PIS", pisID);
		
		if( length > byteBuf.readableBytes() ) {
//			System.out.println("required length : "+ length  + " input length : " + byteBuf.readableBytes());
			logger.debug("D | PIS ID : {} | Must be accepted more data(protocol), length[{}] > readableBytes[{}]", length , pisID , byteBuf.readableBytes());
			return;
		}

		while(byteBuf.readableBytes() >= (length) ) { // Header Read
			byte[] tmp = new byte[length];

			ByteBuf headBuf = byteBuf.readBytes(byteBuf.readableBytes());
			
			headBuf.readBytes(tmp);
			
			PISHeader h = new PISHeader();

//			byte[] compare = h.getDefaultHeader();
//			for(int i=0; i< PISHeader.SIZE; i++) {
//				if( compare[i] != received[i]) {
//					System.out.println("Skip");
//					byteBuf.markReaderIndex();
//					return;
//				}
//			}
			
			byte[] body = new byte[PISBody.SIZE];
			
			for(int i=0; i<PISBody.SIZE; i++) {
				body[i] = tmp[PISHeader.SIZE + i];
			}
			
			PISForm f = new PISForm(h, body);
			
			//패킷 오류체크
			logger.info("CS CHECK ");
			if(f.cs != f.calculatedCS()) 
			{
				logger.info(" FAIL ");
				logger.info("this cs : {} | calcul cs : {} " , f.cs , f.calculatedCS());
				byteBuf.markReaderIndex();
				return;
			}
			else if(f.cs == f.calculatedCS()) 
			{
				logger.info(" SUCCESS ");
				logger.info("this cs : {} |  calcul cs : {} " , f.cs , f.calculatedCS());
				
				
				logger.info(" PIS ID : {} | connection status : {} ", pisID, pisManager.getPISTemplateById(pisID).getVoPRLT_stts().getCMNC_STTS_CD() );
			}
			
			//디코딩까지 완료됐으니 연결상태를 CMS0으로 설정, 그 뒤 업데이트 
			//디코딩, 채널액티브, 프로세싱에서 연결상태 업데이트중임.
			//채널액티브 이외에 전부 주석처리
//			pisConStatus.totalUpdate( pisManager.getPISTemplateById(pisID) );

			logger.info("D | PIS ID : {} | Receive | HASH[{}] : [({}){}]" , pisID , f.hashCode(), f.size(), TypeHelper.byteArrayToHex(f.array(ByteOrder.BIG_ENDIAN))); // 2018.12.10 dsnoh

			MDC.remove("PIS");

			out.add(f);

//			logger.info("D | >>> [ Header : {} | Body : {} ] ", ByteToHex.bytesToHex( h.array(ByteOrder.BIG_ENDIAN) ) , ByteToHex.bytesToHex(f.getBody()) );
			/*
			logger.info("D | >>> [ Header : {} | index : {} | empty : {}({}) | incount : {}({}) | outcount : {}({}) | event : {} | command respons : {} | reserved : {} | vehicleCount : {}({}) | checksum : {} | end : {} ] ",
			ByteToHex.bytesToHex( h.array(ByteOrder.BIG_ENDIAN) ) , 
			ByteToHex.byteToHex( f.index), 
			ByteToHex.stringToHex( Character.toString(f.emptySlot) ), 
			ByteToHex.charToInt( f.emptySlot ),
			ByteToHex.intToHex( f.inCount.intValue() ), 
			f.inCount.intValue() ,
			ByteToHex.intToHex( f.outCount.intValue() ),
			f.outCount.intValue() ,
			ByteToHex.stringToHex( Character.toString(f.EventTrigger)),
			ByteToHex.bytesToHex( f.commandResponse),
			ByteToHex.byteToHex( f.reserved),
			ByteToHex.stringToHex( Character.toString(f.vehicleCount) ),
			(int)f.vehicleCount,
			ByteToHex.byteToHex( f.cs),
			ByteToHex.byteToHex( f.end1) + ByteToHex.byteToHex( f.end2) 
			);
			*/
			
		}		
	}	
	
	public byte calculatedCS(PISForm form) {
		
		byte a = (byte)0x00;
		for(int i=0; i<form.SIZE-3; i++) {
			a += form.body[i];
		}
		return a;
	}
}
