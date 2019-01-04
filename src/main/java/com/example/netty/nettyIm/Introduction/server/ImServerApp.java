package com.example.netty.nettyIm.Introduction.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * IM服务启动入口
 * @author yinjihuan
 */
@SpringBootApplication
public class ImServerApp {
	public static void main(String[] args) {
		int port = 2222;
		new Thread(() -> {
			new ImServer().run(port);
		}).start();
		SpringApplication.run(ImServerApp.class, args);
	}
}
