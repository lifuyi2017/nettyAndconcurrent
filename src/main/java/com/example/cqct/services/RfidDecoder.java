package com.example.cqct.services;

import com.example.cqct.domains.RfidMessage;
import com.example.cqct.util.BigEndian;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @auther:lifuyi
 * @Date: 2018/12/3 10:16
 * @Description:
 */
public class RfidDecoder extends ByteToMessageDecoder {

    private static final Logger logger = LoggerFactory.getLogger(RfidDecoder.class);

    public RfidDecoder() {
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //readableBytes=写标记-读标记
        if(in.readableBytes() >= 2) {
            //记录当前的读标记：this.markedReaderIndex = this.readerIndex;
            in.markReaderIndex();
            byte[] bytes = new byte[2];
            in.readBytes(bytes);
            int bodyStart = BigEndian.getBigEndianShort(bytes);
            if(bodyStart == 23205) {
                //每次读10个字节
                if(in.readableBytes() < 10) {
                    //回滚到之前标记的位置
                    in.resetReaderIndex();
                } else {
                    byte[] descriptionBytes = new byte[10];
                    in.readBytes(descriptionBytes);
                    byte[] padBytes = new byte[2];
                    byte[] verBytes = new byte[2];
                    byte[] typeByte = new byte[2];
                    byte[] lengthBytes = new byte[2];
                    byte[] seqBytes = new byte[2];
                    int padByteIndex = 0;

                    int verByteIndex;
                    for(verByteIndex = 0; verByteIndex < 2; ++verByteIndex) {
                        padBytes[padByteIndex] = descriptionBytes[verByteIndex];
                        ++padByteIndex;
                    }

                    verByteIndex = 0;

                    int typeByteIndex;
                    for(typeByteIndex = 2; typeByteIndex < 4; ++typeByteIndex) {
                        verBytes[verByteIndex] = descriptionBytes[typeByteIndex];
                        ++verByteIndex;
                    }

                    typeByteIndex = 0;

                    int lengthByteIndex;
                    for(lengthByteIndex = 4; lengthByteIndex < 6; ++lengthByteIndex) {
                        typeByte[typeByteIndex] = descriptionBytes[lengthByteIndex];
                        ++typeByteIndex;
                    }

                    lengthByteIndex = 0;

                    int seqByteIndex;
                    for(seqByteIndex = 6; seqByteIndex < 8; ++seqByteIndex) {
                        lengthBytes[lengthByteIndex] = descriptionBytes[seqByteIndex];
                        ++lengthByteIndex;
                    }

                    seqByteIndex = 0;

                    int msgType;
                    for(msgType = 8; msgType < 10; ++msgType) {
                        seqBytes[seqByteIndex] = descriptionBytes[msgType];
                        ++seqByteIndex;
                    }

                    msgType = BigEndian.getBigEndianShort(typeByte);
                    int leftLength = BigEndian.getBigEndianShort(lengthBytes);
                    byte[] leftBytes = new byte[0];
                    if(leftLength > 0) {
                        leftBytes = new byte[leftLength];
                    }

                    if(leftLength > 0 && in.readableBytes() < leftLength) {
                        in.resetReaderIndex();
                    } else {
                        if(leftLength > 0) {
                            in.readBytes(leftBytes);
                        }

                        RfidMessage rfidMessage = new RfidMessage();
                        rfidMessage.setPad(BigEndian.getBigEndianShort(padBytes));
                        rfidMessage.setVer(BigEndian.getBigEndianShort(verBytes));
                        rfidMessage.setType(msgType);
                        rfidMessage.setSeq(BigEndian.getBigEndianShort(seqBytes));
                        switch(msgType) {
                            case 1:
                                rfidMessage.setRfidJsonStr(new String(leftBytes, "UTF-8"));
                                break;
                            case 2:
                            case 8193:
                                int resByteIndex = 0;
                                byte[] resBytes = new byte[2];

                                for(int resIndex = 0; resIndex < 2; ++resIndex) {
                                    resBytes[resByteIndex] = leftBytes[resIndex];
                                    ++resByteIndex;
                                }

                                rfidMessage.setRes(BigEndian.getBigEndianShort(resBytes));
                            case 1037:
                            case 1038:
                                break;
                            default:
                                logger.error("rfid invalid message type");
                        }

                        out.add(rfidMessage);
                    }
                }
            }
        }
    }

}
