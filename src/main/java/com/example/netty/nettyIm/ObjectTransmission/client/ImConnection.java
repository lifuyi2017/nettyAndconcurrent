package com.example.netty.nettyIm.ObjectTransmission.client;

import com.example.netty.nettyIm.ObjectTransmission.MessageDecoder;
import com.example.netty.nettyIm.ObjectTransmission.MessageEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author:lifuyi
 * @Date: 2018/12/13 11:08
 * @Description:
 */
public class ImConnection {

    private SocketChannel channel;
    public SocketChannel connect(String host, int port) {
        doConnect(host, port);
        return this.channel;
    }

    private void doConnect(String host, int port) {

        try {
            EventLoopGroup workerGroup = new NioEventLoopGroup();

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("decoder", new MessageDecoder());
                            socketChannel.pipeline().addLast("encoder", new MessageEncoder());
                            socketChannel.pipeline().addLast(new ClientPoHandler());
                        }
                    });
            ChannelFuture f = bootstrap.connect(host, port).sync();
            channel= (SocketChannel) f.channel();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
