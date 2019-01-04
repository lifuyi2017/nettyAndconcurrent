package com.example.netty.nettyIm.Introduction.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @auther:lifuyi
 * @Date: 2018/12/4 12:08
 * @Description:
 */
public class ServerStringHandler  extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("ssssssssssssssssssssss");
        System.err.println("server:" + msg.toString());
        ctx.writeAndFlush(msg.toString() + "你好");
//        ctx.writeAndFlush(Unpooled.copiedBuffer((msg.toString() + "你好").getBytes()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
