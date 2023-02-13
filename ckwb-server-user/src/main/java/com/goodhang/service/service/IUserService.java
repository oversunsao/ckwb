package com.goodhang.service.service;

import com.goodhang.service.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.goodhang.service.pojo.enums.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2022-09-20
 */
public interface IUserService extends IService<User> {
    //注册
    RespBean reg (User user);

    //根据电话号码查询
    User setUser(String s);

    //登录
    RespBean Login(User user);
}
