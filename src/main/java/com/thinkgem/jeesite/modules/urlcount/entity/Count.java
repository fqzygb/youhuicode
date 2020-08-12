package com.thinkgem.jeesite.modules.urlcount.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

public class Count extends DataEntity<Count> {
    private String id;
    private String ipAddress;
    private  String ipUrl;
    private String reMarks;
    private Date insertTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpUrl() {
        return ipUrl;
    }

    public void setIpUrl(String ipUrl) {
        this.ipUrl = ipUrl;
    }

    public String getReMarks() {
        return reMarks;
    }

    public void setReMarks(String reMarks) {
        this.reMarks = reMarks;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
}
