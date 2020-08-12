package com.thinkgem.jeesite.modules.urlcount.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.urlcount.dao.CountDao;
import com.thinkgem.jeesite.modules.urlcount.entity.Count;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CountService extends CrudService<CountDao, Count> {
    @Autowired
    private CountDao countDao;
    public Integer getCountByUrl(String url){

        return countDao.getCountByUrl(url);

    }

    @Transactional(readOnly = false)
    public int  insert(Count count) {
     return    countDao.insert(count) ;
    }


}
