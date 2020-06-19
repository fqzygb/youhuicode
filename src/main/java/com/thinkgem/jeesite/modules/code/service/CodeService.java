/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.code.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.code.entity.Code;
import com.thinkgem.jeesite.modules.code.dao.CodeDao;

/**
 * 码Service
 * @author 冯琪增
 * @version 2020-06-19
 */
@Service
@Transactional(readOnly = true)
public class CodeService extends CrudService<CodeDao, Code> {

	@Autowired
	private CodeDao codeDao;

	public Code get(String id) {
		return super.get(id);
	}

	public List<Code> findList(Code code) {
		return super.findList(code);
	}

	public Page<Code> findPage(Page<Code> page, Code code) {
		return super.findPage(page, code);
	}

	@Transactional(readOnly = false)
	public void save(Code code) {
		super.save(code);
	}

	@Transactional(readOnly = false)
	public void delete(Code code) {
		super.delete(code);
	}

	public Code getEntity() {
		return codeDao.getEnty();
	}


	public Code getEntityByTag() {
		return codeDao.getEntityByTag();
	}

	public void update(Code codeTag) {
		codeDao.updateByEnty(codeTag);
	}
}
