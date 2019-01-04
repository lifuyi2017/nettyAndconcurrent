package com.example.netty.protobuf.server;

import com.example.netty.protobuf.MessageProto;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

/**
 * IM服务启动
 * @author yinjihuan
 *
 */
public class ImServer {
	
	public void run(int port) {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
        		.channel(NioServerSocketChannel.class)
        		.childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                    	// 实体类传输数据，protobuf序列化·
                    	ch.pipeline().addLast("decoder",  
                                new ProtobufDecoder(MessageProto.Message.getDefaultInstance()));
                    	ch.pipeline().addLast("encoder",  
                                new ProtobufEncoder());
                    	ch.pipeline().addLast(new ServerPoHandlerProto());
                    }
                })
        		.option(ChannelOption.SO_BACKLOG, 128)
				//这个选项用于可能长时间没有数据交流的连接。当设置该选项以后，如果在两小时内没有数据的通信时，TCP会自动发送一个活动探测数据报文。
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        try {
			ChannelFuture f = bootstrap.bind(port).sync();
			 f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
	}
	
}
