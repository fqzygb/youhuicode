/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.click.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.click.entity.ClickTime;

/**
 * 点击时间DAO接口
 * @author 冯琪增
 * @version 2020-06-20
 */
@MyBatisDao
public interface ClickTimeDao extends CrudDao<ClickTime> {
    void inser(ClickTime clickTime);

}
