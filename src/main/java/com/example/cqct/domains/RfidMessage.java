package com.example.cqct.domains;

/**
 * @auther:lifuyi
 * @Date: 2018/12/3 10:20
 * @Description:
 */
public class RfidMessage {
    public static final int MSG_MAGIC_FLAG = 23205;
    public static final int MSG_PAD_FLAG = 0;
    public static final int MSG_VER_FLAG = 1;
    public static final int MSG_CONNECT_FLAG = 8193;
    public static final int MSG_HEARTBEAT_REQUEST_FLAG = 1037;
    public static final int MSG_HEARTBEAT_RESPONSE_FLAG = 1038;
    public static final int MSG_RFID_REQUEST_FLAG = 1;
    public static final int MSG_RFID_RESPONSE_FLAG = 2;
    private int type;
    private int res;
    private int pad;
    private int ver;
    private int seq;
    private String rfidJsonStr;

    public RfidMessage() {
    }

    public int getPad() {
        return this.pad;
    }

    public void setPad(int pad) {
        this.pad = pad;
    }

    public String getRfidJsonStr() {
        return this.rfidJsonStr;
    }

    public void setRfidJsonStr(String rfidJsonStr) {
        this.rfidJsonStr = rfidJsonStr;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRes() {
        return this.res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public int getVer() {
        return this.ver;
    }

    public void setVer(int ver) {
        this.ver = ver;
    }

    public int getSeq() {
        return this.seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
}
