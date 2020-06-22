/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.click.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.click.entity.ClickTime;
import com.thinkgem.jeesite.modules.click.dao.ClickTimeDao;

/**
 * 点击时间Service
 * @author 冯琪增
 * @version 2020-06-20
 */
@Service
@Transactional(readOnly = true)
public class ClickTimeService extends CrudService<ClickTimeDao, ClickTime> {

	@Autowired
	private ClickTimeDao clickTimeDao;

	public ClickTime get(String id) {
		return super.get(id);
	}

	public List<ClickTime> findList(ClickTime clickTime) {
		return super.findList(clickTime);
	}

	public Page<ClickTime> findPage(Page<ClickTime> page, ClickTime clickTime) {
		return super.findPage(page, clickTime);
	}

	@Transactional(readOnly = false)
	public void save(ClickTime clickTime) {
		super.save(clickTime);
	}

	@Transactional(readOnly = false)
	public void delete(ClickTime clickTime) {
		super.delete(clickTime);
	}


	@Transactional(readOnly = false)
	public void insert(ClickTime clickTime) {
		 clickTimeDao.insert(clickTime) ;
	}
}
