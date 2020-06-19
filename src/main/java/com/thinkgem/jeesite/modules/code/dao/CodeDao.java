/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.code.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.code.entity.Code;

/**
 * 码DAO接口
 * @author 冯琪增
 * @version 2020-06-19
 */
@MyBatisDao
public interface CodeDao extends CrudDao<Code> {

    Code getEnty();

    Code getEntityByTag();

    void updateByEnty(Code codeTag);
}
