package com.util;

import java.net.InetSocketAddress;
import io.netty.channel.Channel;


public abstract class NettyHelper {


	/**
	 * 채널정보 리턴
	 * @param ch
	 * @return
	 */
	public static String getAddress(Channel ctx){
		/*String localIp = ((InetSocketAddress)ch.localAddress()).getAddress().toString().substring(1);
		int localPort = ((InetSocketAddress)ch.localAddress()).getPort();
		String remoteIp = ((InetSocketAddress)ch.remoteAddress()).getAddress().toString().substring(1);
		int remotePort = ((InetSocketAddress)ch.remoteAddress()).getPort();
		int parentPort = ((InetSocketAddress)ch.parent().localAddress()).getPort();*/
		
		String s = "[Remote #IP("+getRemoteAddress(ctx) +") #Port("+getRemotePort(ctx)+")]"
				  + " #[Local IP("+getLocalAddress(ctx) +") #Port("+getLocalPort(ctx)+")]";

		return s;
	}
	
	
	/**
	 * 원격지 IP 주소를 문자열로 리턴
	 * @param channelHandlerContext
	 * @return
	 */
	
	public static String getRemoteAddress(Channel session) {
		String remoteAddress = null; // 원격지 주소

		InetSocketAddress inetAddr = (InetSocketAddress)session.remoteAddress();
		if(inetAddr != null) {
			remoteAddress = inetAddr.getAddress().getHostAddress();
		} else {
			remoteAddress = "unknown";
		}

		return remoteAddress;
	}
	/**
	 * 원격지 PORT 번호를 리턴
	 * @param channelHandlerContext
	 * @return
	 */
	public static int getRemotePort(Channel session) {
		int remotePort = 0; // 원격지 포트
		InetSocketAddress inetAddr = (InetSocketAddress)session.remoteAddress();
		if(inetAddr != null) {
			remotePort = inetAddr.getPort();
		} 

		return remotePort;
	}
	
	/**
	 * 로컬 IP 주소를 문자열로 리턴
	 * @param channelHandlerContext
	 * @return
	 */
	public static String getLocalAddress(Channel session) {
		String localAddress = null; // 원격지 주소
		InetSocketAddress inetAddr = (InetSocketAddress)session.localAddress();
		if(inetAddr != null) {
			localAddress = inetAddr.getAddress().getHostAddress();
		} else {
			localAddress = "unknown";
		}

		return localAddress;
	}
	/**
	 * 로컬 PORT 번호를 리턴
	 * @param channelHandlerContext
	 * @return
	 */
	public static int getLocalPort(Channel session) {
		int localPort = 0; 
		
		InetSocketAddress inetAddr = (InetSocketAddress)session.localAddress();
		if(inetAddr != null) {
			localPort = inetAddr.getPort();
		} 
		return localPort;
	}
}