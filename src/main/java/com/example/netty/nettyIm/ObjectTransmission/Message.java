package com.example.netty.nettyIm.ObjectTransmission;

import java.io.Serializable;

/**
 * @author:lifuyi
 * @Date: 2018/12/12 17:32
 * @Description:
 */
public class Message implements Serializable {


    private static final long serialVersionUID = 6103261766570282009L;

    private String id;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
