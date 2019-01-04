package com.example.netty.nettyIm.Introduction.client;


import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;

import java.util.UUID;

/**
 * IM 客户端启动入口
 * @author yinjihuan
 * http://cxytiandi.com/blog/detail/17345
 */
public class ImClientApp {
	public static final String HOST = "127.0.0.1";
	public static int PORT = 2222;
	public static void main(String[] args) {
		SocketChannel channel = new ImConnection().connect(HOST, PORT);
//		channel.writeAndFlush(Unpooled.copiedBuffer(("yinjihuan").getBytes()));
		channel.writeAndFlush("yinjihuan");
	}
}
