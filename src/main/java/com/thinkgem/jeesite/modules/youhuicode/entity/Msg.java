package com.thinkgem.jeesite.modules.youhuicode.entity;

public class Msg {

    //标识 1表示成功  0表示失败
    private String flg;
    //提示内容
    private String msgContent;

    //怎么生成set，get方法  构造方法，有参数和无参
    //?


    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Msg(String flg, String msgContent) {
        this.flg = flg;
        this.msgContent = msgContent;
    }

    public Msg() {
    }
}
