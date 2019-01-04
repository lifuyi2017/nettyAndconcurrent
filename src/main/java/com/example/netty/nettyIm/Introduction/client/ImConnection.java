package com.example.netty.nettyIm.Introduction.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import io.netty.channel.Channel;

import java.nio.charset.Charset;

/**
 * @auther:lifuyi
 * @Date: 2018/12/4 12:10
 * @Description:
 */
public class ImConnection {

    private SocketChannel  channel;
    public SocketChannel connect(String host, int port) {
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
                    ch.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
                    ch.pipeline().addLast(new StringEncoder(Charset.forName("UTF-8")));
                    ch.pipeline().addLast(new ClientStringHandler());
                }
            });
            ChannelFuture f = b.connect(host, port).sync();
            if(f.isSuccess()){
                System.out.println("链接成功");
            }
            channel= (SocketChannel) f.channel();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
