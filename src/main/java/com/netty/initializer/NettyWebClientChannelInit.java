package com.netty.initializer;

import javax.net.ssl.SSLException;

import com.handler.NettyHttpHandler;

import HTTPTest.HttpSnoopClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

public class NettyWebClientChannelInit extends ChannelInitializer<SocketChannel>{
    
    private boolean ssl = false;
    
    SslContext sslCtx;
    
    private EventLoopGroup group;
    
     public NettyWebClientChannelInit(EventLoopGroup group) {
         this.group = group;
    }
     
     public NettyWebClientChannelInit(SslContext sslCtx) {
         this.sslCtx = sslCtx;
     }
    @Override
    protected void initChannel(SocketChannel sc) throws Exception {
        // TODO Auto-generated method stub
        
        ChannelPipeline p = sc.pipeline();
        if(ssl) {
            SslContext sslCtx = null;
            try {
                sslCtx = SslContextBuilder.forClient()
                        .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
                 p.addLast(sslCtx.newHandler(sc.alloc()));
            } catch (SSLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        
        // Enable HTTPS if necessary.
        if (sslCtx != null) {
            p.addLast(sslCtx.newHandler(sc.alloc()));
        }
    
    //    p.addLast(new MessageDecoder());
         
        //chunked 된 응답을 집계하는 코덱
//        p.addLast("chunked",new HttpObjectAggregator(1048576));
//        p.addLast("codec",new HttpClientCodec());
//        p.addLast(new NettyHttpHandler(group, sc));
        
        p.addLast(new HttpClientCodec());

        // Remove the following line if you don't want automatic content decompression.
        p.addLast(new HttpContentDecompressor());

        // Uncomment the following line if you don't want to handle HttpContents.
        //p.addLast(new HttpObjectAggregator(1048576));

        p.addLast(new HttpSnoopClientHandler());
        
    }
}
