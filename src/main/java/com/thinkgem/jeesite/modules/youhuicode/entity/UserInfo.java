/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.youhuicode.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 优惠码Entity
 * @author 冯琪增
 * @version 2020-06-19
 */
public class UserInfo extends DataEntity<UserInfo> {
	
	private static final long serialVersionUID = 1L;
	private String serialNumber;		// serial_number
	private String psptId;		// pspt_id
	private String tag;		// 0未领取
	private String code;		// 兑换码
	
	public UserInfo() {
		super();
	}

	public UserInfo(String id){
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
	
	@Length(min=1, max=1, message="0未领取长度必须介于 1 和 1 之间")
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	@Length(min=0, max=64, message="兑换码长度必须介于 0 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}