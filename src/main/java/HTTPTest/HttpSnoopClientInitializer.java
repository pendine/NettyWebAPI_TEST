package HTTPTest;

import com.network.netty.codec.JsonDecoder;
import com.network.netty.codec.WebDecoder;

/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.ssl.SslContext;

public class HttpSnoopClientInitializer extends ChannelInitializer<SocketChannel> {

    private final SslContext sslCtx;

    public HttpSnoopClientInitializer(SslContext sslCtx) {
    	
        this.sslCtx = sslCtx;
        
    }

    @Override
    public void initChannel(SocketChannel ch) {
    	
    	System.out.println("HttpSnoopClientInitializer | initChannel ");
        ChannelPipeline p = ch.pipeline();

        System.out.println("pipeline set after");
        // Enable HTTPS if necessary.
        if (sslCtx != null) {
            p.addLast( sslCtx.newHandler( ch.alloc() ) );
        }

        p.addLast( new HttpClientCodec() );

        System.out.println("codec set after ");
        // Remove the following line if you don't want automatic content decompression.
//        p.addLast( new HttpContentDecompressor() );
        

        p.addLast( new WebDecoder() );
//        p.addLast( new JsonDecoder() );
//        p.addLast( new HttpJSONHandler() );
        System.out.println("decoder set after");
        // Uncomment the following line if you don't want to handle HttpContents.
//        p.addLast(new HttpObjectAggregator(1048576));
        p.addLast( new HttpSnoopClientHandler() );
        
        System.out.println("handler set after");
        
    }
}

