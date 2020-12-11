package com.network.netty.bootstrap;

import io.netty.channel.EventLoopGroup;

public interface INettyBootstrapFactory <T> {

	public T createBootstrap() throws Exception;
	
//	public void ShutDown();
	
}
