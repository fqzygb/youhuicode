package com.thinkgem.jeesite.modules.zhifubao.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

public class NumberInfo extends DataEntity<NumberInfo> {
    private String id;
    private String userId;//支付宝user_id
    private String phoneNumber;
    private String psptId;
    private Date orderTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPsptId() {
        return psptId;
    }

    public void setPsptId(String psptId) {
        this.psptId = psptId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
}
