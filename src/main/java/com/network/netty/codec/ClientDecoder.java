package com.network.netty.codec;

import java.nio.ByteOrder;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.domain.PISTemplate;
import com.network.netty.NettyTCPClient;
import com.util.ApplicationContextProvider;
import com.util.ByteToHex;
import com.util.NettyHelper;
import com.util.TypeHelper;
import com.vo.voPRLT_CTLR_STTS;
import com.protocol.client.ClientBody;
import com.protocol.client.ClientForm;
import com.protocol.client.ClientHeader;
import com.protocol.client.ClientTail;
import com.protocol.pis.PISForm;
import com.protocol.pis.PISHeader;
import com.service.PISManager;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.http.HttpRequestDecoder;

public class ClientDecoder extends HttpRequestDecoder {

	private static final Logger logger = LoggerFactory.getLogger(ClientDecoder.class);


	@Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
		
		super.decode(ctx, buffer, out);
	}
	
}
