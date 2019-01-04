package com.example.netty.nettyIm.ObjectTransmission;

import com.example.netty.nettyIm.util.ByteArrayUtils;
import com.example.netty.nettyIm.util.ByteUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.Optional;

/**
 * @author:lifuyi
 * @Date: 2018/12/12 17:36
 * @Description:
 */
public class MessageEncoder extends MessageToByteEncoder<Message> {


    @Override
    protected void encode(ChannelHandlerContext ctx, Message message, ByteBuf out) throws Exception {
//        byte[] datas = ByteUtils.objectToByte(message);
        Optional<byte[]> bytes = ByteArrayUtils.objectToBytes(message);
        byte[] bytes1 = bytes.get();
        out.writeBytes(bytes1);
        ctx.flush();
    }

}
