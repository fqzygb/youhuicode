/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.youhuicode.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.youhuicode.entity.UserInfo;

/**
 * 优惠码DAO接口
 * @author 冯琪增
 * @version 2020-06-18
 */
@MyBatisDao
public interface UserInfoDao extends CrudDao<UserInfo> {

    //获取当前用户的信息 明白哦？OK
    //然后去编写mapper文件，
    UserInfo getUserInfo(UserInfo userInfo);

    void updateByEnty(UserInfo info);
//    UserInfo updateUserInfo(UserInfo userInfo);

}
