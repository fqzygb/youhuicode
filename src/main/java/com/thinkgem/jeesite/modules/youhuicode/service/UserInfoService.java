/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.youhuicode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.youhuicode.entity.UserInfo;
import com.thinkgem.jeesite.modules.youhuicode.dao.UserInfoDao;

/**
 * 优惠码Service
 * @author 冯琪增
 * @version 2020-06-18
 */
@Service
@Transactional(readOnly = true)
public class UserInfoService extends CrudService<UserInfoDao, UserInfo> {

	@Autowired
	private UserInfoDao userInfoDao;

	public UserInfo get(String id) {
		return super.get(id);
	}

	public List<UserInfo> findList(UserInfo userInfo) {
		return super.findList(userInfo);
	}

	public Page<UserInfo> findPage(Page<UserInfo> page, UserInfo userInfo) {
		return super.findPage(page, userInfo);
	}

	@Transactional(readOnly = false)
	public void save(UserInfo userInfo) {
		super.save(userInfo);
	}

	@Transactional(readOnly = false)
	public void delete(UserInfo userInfo) {
		super.delete(userInfo);
	}

//	@Transactional(readOnly = false)
//	public UserInfo updateUserInfo(UserInfo userInfo) {
//		return userInfoDao.updateUserInfo(userInfo);
//
//	}


	//去获取当前用户的信息，明白吗OK ，但是怎么爆红
	//那是因为你现在还没有这个方法OK
	public UserInfo getUserInfo(UserInfo userInfo) {
		return userInfoDao.getUserInfo(userInfo);
	}


	public void update(UserInfo info) {
		userInfoDao.updateByEnty(info);
	}
}
