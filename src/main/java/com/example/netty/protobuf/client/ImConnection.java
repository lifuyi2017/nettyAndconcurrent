package com.example.netty.protobuf.client;

import com.example.netty.protobuf.MessageProto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class ImConnection {

	private Channel channel;
	
	public Channel connect(String host, int port) {
		doConnect(host, port);
		return this.channel;
	}

	private void doConnect(String host, int port) {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					// 实体类传输数据，protobuf序列化
					ch.pipeline().addLast("ping", new IdleStateHandler(60, 20, 60 * 10, TimeUnit.SECONDS));
                	ch.pipeline().addLast("decoder",  
                            new ProtobufDecoder(MessageProto.Message.getDefaultInstance()));
                	ch.pipeline().addLast("encoder",  
                            new ProtobufEncoder());
                	ch.pipeline().addLast(new ClientPoHandlerProto());
				}
			});

			ChannelFuture f = b.connect(host, port);
			f.addListener(new ConnectionListener());
			channel = f.channel();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
