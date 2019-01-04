package com.example.netty.nettyIm.ObjectTransmission;

import com.example.netty.nettyIm.util.ByteArrayUtils;
import com.example.netty.nettyIm.util.ByteUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.Optional;

/**
 * @author:lifuyi
 * @Date: 2018/12/13 10:12
 * @Description:
 */
public class MessageDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        String string = ByteArrayUtils.toHexString(ByteUtils.read(in));
        byte[] r=ByteArrayUtils.toByteArray(string);
        Message message = (Message) ByteArrayUtils.bytesToObject(r).get();
        out.add(message);
    }

}
