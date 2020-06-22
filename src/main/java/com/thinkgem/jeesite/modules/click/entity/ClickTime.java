/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.click.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 点击时间Entity
 * @author 冯琪增
 * @version 2020-06-20
 */
public class ClickTime extends DataEntity<ClickTime> {
	
	private static final long serialVersionUID = 1L;
	private String serialNumber;		// serial_number
	private String psptId;		// pspt_id
	private Date clicktime;		// clicktime
	
	public ClickTime() {
		super();
	}

	public ClickTime(String id){
		super(id);
	}

	@Length(min=0, max=64, message="serial_number长度必须介于 0 和 64 之间")
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	@Length(min=0, max=128, message="pspt_id长度必须介于 0 和 128 之间")
	public String getPsptId() {
		return psptId;
	}

	public void setPsptId(String psptId) {
		this.psptId = psptId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getClicktime() {
		return clicktime;
	}

	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	
}