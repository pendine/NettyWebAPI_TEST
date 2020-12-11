package com.handler;

import java.nio.ByteOrder;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enums.eOPOpcode;
import com.protocol.client.ClientBody;
import com.protocol.client.ClientForm;
import com.protocol.client.ClientHeader;
import com.protocol.client.ClientTail;
import com.util.ApplicationContextProvider;
import com.util.ByteToHex;
import com.util.LogHelper;
import com.util.NettyHelper;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;


public class PISIdleStateHandler extends IdleStateHandler {

	private static final Logger logger = LoggerFactory.getLogger(PISIdleStateHandler.class);
		
	
	
	Properties contextProperties = (Properties)ApplicationContextProvider.getApplicationContext().getBean("contextProperties");	
	
	public PISIdleStateHandler(int readerIdleTimeSeconds,int writerIdleTimeSeconds, int allIdleTimeSeconds) {
		super(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds);
		// TODO Auto-generated constructor stub
	}
			
	@Override
	protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
		if (evt.state() == IdleState.ALL_IDLE ) 
		{		
			logger.info("Terminate All idle channel | #Remote[{}:{}:{}]", 
					 NettyHelper.getRemoteAddress(ctx.channel()), NettyHelper.getRemotePort(ctx.channel()), ctx.toString());
		}
		else if(evt.state() == IdleState.READER_IDLE) 
		{
			logger.info("Terminate Reader idle channel | #Remote[{}:{}:{}]", 
					 NettyHelper.getRemoteAddress(ctx.channel()), NettyHelper.getRemotePort(ctx.channel()), ctx.toString());
		}
		else if(evt.state() == IdleState.WRITER_IDLE) 
		{
			logger.info("Writer idle channel | #Remote[{}:{}:{}] ",  NettyHelper.getRemoteAddress(ctx.channel()), NettyHelper.getRemotePort(ctx.channel()), ctx.toString());
		}
		
		super.channelIdle(ctx, evt);
		
	}

	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String ip = NettyHelper.getRemoteAddress(ctx.channel());

		logger.info("채널값 세팅  ctx : " + ctx.toString() + " channel 값 : " + ctx.channel().toString() + " channel hashcode : " + ctx.channel().hashCode() );
		
		ClientForm clientForm = new ClientForm();
		ctx.channel().writeAndFlush(clientForm);

		//테스트용 -- 커넥션 연결됐을때를 알기 위한 업데이트처리 
		//디코딩, 채널액티브, 프로세싱에서 연결상태 업데이트중임.
		//채널액티브 이외에 전부 주석처리

		super.channelActive(ctx);
		
	}

	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		logger.info("Channel Inactive, #Remote[{}:{}:{}]", 
					NettyHelper.getRemoteAddress(ctx.channel()), 
					NettyHelper.getRemotePort(ctx.channel()),
					ctx.hashCode()
					);
		
		super.channelInactive(ctx);
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.error("Channel Exception. #e:{}, #Remote[{}:{}] ", 
				LogHelper.getPrintStackTrace(cause),
				NettyHelper.getRemoteAddress(ctx.channel()), NettyHelper.getRemotePort(ctx.channel()));
		
		super.exceptionCaught(ctx, cause);
	}
	
	@Override
	public void close(ChannelHandlerContext ctx, ChannelPromise future) throws Exception {
		logger.info("UserIdleStateHandler : ChannelClosed - Channel #{} : {}", ctx.channel().hashCode() ,ctx.channel().remoteAddress());	
		
		super.close(ctx, future);
		
	}
	
	
	public static void disconnectOnFlushChannel(Channel ch) 
	{
		if(!ch.isActive()) return;
		
		logger.info("[DISCONNECT-On-Flush] Start to disconnect channel #hascode:{} #ip: {}", ch.hashCode(),ch.remoteAddress().toString());
		
		ch.flush();
		
    	ChannelFuture f = ch.disconnect().awaitUninterruptibly();
    	
    	if(f.isDone())
    	{
    		logger.info("[DISCONNECT-On-Flush] Done channel #hascode:{} #ip: {}",ch.hashCode(), ch.remoteAddress().toString()); 
    	}
    	else
    	{
    		logger.info("[DISCONNECT-On-Flush] Fail 1 channel #hascode:{} #ip: {}",ch.hashCode(), ch.remoteAddress().toString()); 
    	}
    	if(f.isSuccess())
    	{
    		logger.info("[DISCONNECT-On-Flush] Success channel #hascode:{} #ip: {}",ch.hashCode(), ch.remoteAddress().toString()); 
    	}
    	else
    	{
    		logger.info("[DISCONNECT-On-Flush] Fail 2 channel #hascode:{} #ip: {}",ch.hashCode(), ch.remoteAddress().toString());
    	}
    	
	}	
	
}
