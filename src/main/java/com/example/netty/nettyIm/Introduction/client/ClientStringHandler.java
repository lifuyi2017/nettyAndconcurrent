package com.example.netty.nettyIm.Introduction.client;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @auther:lifuyi
 * @Date: 2018/12/4 12:11
 * @Description:
 */
public class ClientStringHandler extends ChannelHandlerAdapter {

    static final String connectMessage="Hi weclome,lifuyi";

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("client:" + msg.toString());
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

/*
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i=0;i<5;i++){
            ctx.writeAndFlush(Unpooled.copiedBuffer(connectMessage.getBytes()));
        }
    }
*/

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
