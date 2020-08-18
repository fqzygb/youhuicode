package com.thinkgem.jeesite.modules.zhifubao.entity;

import java.util.List;

public class Message {
    private String status;
    private String msgContent;
    private List<Object> data;

    public Message() {
    }

    public Message(String status, String msgContent, List<Object> data) {
        this.status = status;
        this.msgContent = msgContent;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
