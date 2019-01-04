package com.example.netty.protobuf.server;

import com.example.netty.protobuf.ConnectionPool;
import com.example.netty.protobuf.MessageProto;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @auther:lifuyi
 * @Date: 2018/12/4 10:43
 * @Description:
 */
public class ServerPoHandlerProto extends ChannelInboundHandlerAdapter  {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageProto.Message message = (MessageProto.Message) msg;
        if (ConnectionPool.getChannel(message.getId()) == null) {
            ConnectionPool.putChannel(message.getId(), ctx);
        }
        System.err.println("server:" + message.getId());
        ctx.writeAndFlush(message);
    }
}
