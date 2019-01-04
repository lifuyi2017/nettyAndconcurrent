package com.example.netty.nettyIm.ObjectTransmission.client;


import com.example.netty.nettyIm.ObjectTransmission.Message;
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
		Message message=new Message();
		message.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		message.setContent("test");
		channel.writeAndFlush(message);
	}
}
