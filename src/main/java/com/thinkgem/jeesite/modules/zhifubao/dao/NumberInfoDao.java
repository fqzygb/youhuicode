package com.thinkgem.jeesite.modules.zhifubao.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zhifubao.entity.NumberInfo;

import java.util.HashMap;
import java.util.List;

@MyBatisDao
public interface NumberInfoDao extends CrudDao<NumberInfo> {

    int insert(NumberInfo numberInfo) ;
    List<NumberInfo> findByAll(NumberInfo numberInfo);
    List<NumberInfo> getEnty();
    List<NumberInfo> getEntyByTime(HashMap map);
}
