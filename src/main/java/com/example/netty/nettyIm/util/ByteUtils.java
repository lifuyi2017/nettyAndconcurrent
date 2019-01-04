package com.example.netty.nettyIm.util;

import com.example.netty.nettyIm.ObjectTransmission.Message;
import io.netty.buffer.ByteBuf;

import java.io.*;
import java.util.Optional;

/**
 * @author:lifuyi
 * @Date: 2018/12/13 10:00
 * @Description:
 */
public class ByteUtils {

    public static byte[] read(ByteBuf datas) {
        byte[] bytes = new byte[datas.readableBytes()];
        datas.readBytes(bytes);
        return bytes;
    }

    public static Object byteToObject(byte[] bytes) {
        Object obj = null;
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        ObjectInputStream oi = null;
        try {
            oi = new ObjectInputStream(bi);
            obj = oi.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                oi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    public static void main(String[] args) {
        Message message = new Message();
        message.setId("aaaa");
        message.setContent("lifuyi");
        byte[] bytes = ByteUtils.objectToByte(message);
        Message o = (Message)ByteUtils.byteToObject(bytes);
        System.out.println(o.getId());
        System.out.println(o.getContent());
    }


    public static byte[] objectToByte(Object obj) {
        byte[] bytes = null;
        //字节数组输出流在内存中创建一个字节数组缓冲区，所有发送到输出流的数据保存在该字节数组缓冲区中
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        //对象的输出流
        ObjectOutputStream oo = null;
        try {
            oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes = bo.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                oo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }
}
