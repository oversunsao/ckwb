package com.goodhang.service.service;

import com.goodhang.service.pojo.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.goodhang.service.pojo.enums.RespBean;

/**
 * <p>
 * 用户详情表 服务类
 * </p>
 *
 * @author jobob
 * @since 2022-09-30
 */
public interface IUserInfoService extends IService<UserInfo> {

    //根据token查询用户详情
    RespBean getUserInfo();
    //根据token查询用户详情id
    int getid();
    //创建时调用
    boolean setUserInfo(String t);
    //修改个人信息
    RespBean UpdateUserInfo(UserInfo userInfo);

}
