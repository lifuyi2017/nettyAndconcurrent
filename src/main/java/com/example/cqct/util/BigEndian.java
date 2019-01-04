package com.example.cqct.util;

/**
 * @auther:lifuyi
 * @Date: 2018/12/3 10:18
 * @Description:
 */
public class BigEndian {

    public BigEndian() {
    }

    public static byte[] toBigEndianShort(int number) {
        byte[] bytes = new byte[]{(byte)(number >>> 8), (byte)number};
        return bytes;
    }

    public static int getBigEndianShort(byte[] bytes) {
        int b0 = bytes[0] & 255;
        int b1 = bytes[1] & 255;
        return b1 + (b0 << 8);
    }

    public static byte[] toBigEndian(int number) {
        byte[] bytes = new byte[]{(byte)(number >>> 24), (byte)(number >>> 16), (byte)(number >>> 8), (byte)number};
        return bytes;
    }

    public static int getBigEndianInt(byte[] bytes) {
        int b0 = bytes[0] & 255;
        int b1 = bytes[1] & 255;
        int b2 = bytes[2] & 255;
        int b3 = bytes[3] & 255;
        return b3 + (b2 << 8) + (b1 << 16) + (b0 << 24);
    }

}
