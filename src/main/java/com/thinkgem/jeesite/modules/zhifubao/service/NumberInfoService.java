package com.thinkgem.jeesite.modules.zhifubao.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.zhifubao.dao.NumberInfoDao;

import com.thinkgem.jeesite.modules.zhifubao.entity.NumberInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;


@Service
public class NumberInfoService extends CrudService<NumberInfoDao, NumberInfo> {
    @Autowired
    private NumberInfoDao numberInfoDao;

    @Transactional(readOnly = false)
    public int insert(NumberInfo numberInfo){
        return numberInfoDao.insert(numberInfo);
    }

    public List<NumberInfo> getEntity() {
        return numberInfoDao.getEnty();
    }
    public List<NumberInfo> getEntyByTime(HashMap map) {
        return numberInfoDao.getEntyByTime(map);
    }

}
