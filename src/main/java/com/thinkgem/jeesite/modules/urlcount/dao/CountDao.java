package com.thinkgem.jeesite.modules.urlcount.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.urlcount.entity.Count;
@MyBatisDao
public interface CountDao extends CrudDao<Count> {
    Integer getCountByUrl(String url);
    int insert(Count count);


}
