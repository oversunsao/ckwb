package com.goodhang.service.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goodhang.service.pojo.User;
import com.goodhang.service.mapper.UserMapper;
import com.goodhang.service.pojo.enums.RespBean;
import com.goodhang.service.service.IUserInfoService;
import com.goodhang.service.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-09-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private IUserInfoService iUserInfoService;
    RespBean respBean ;
    @Value("${jwt.secret}")
    private String secret;

    //根据电话号码查询
    @Override
    public User setUser(String s) {
        return this.baseMapper.selectOne(new QueryWrapper<User>().eq("telephone",s));
    }
    @Override
    public RespBean Login(User user) {
        try {
            User user1=setUser(user.getTelephone());
            if (!(user1.equals(""))) {
                if(user.getUserPassword().equals(user1.getUserPassword())){
                    String token = JWT.create()
                            .setPayload("id", user.getId())
                            .setPayload("telephone", user.getTelephone())
                            .setKey(secret.getBytes())
                            .sign();
                    return respBean.success("登录成功",token);
                }else {
                    return respBean.error("密码错误");
                }
            }else {
                return respBean.error("账号为空");
            }
        }catch (Exception e){
            return respBean.error("登录异常");
        }
    }

    //注册
    @Override
    public RespBean reg(User user) {
        try {
            if(setUser(user.getTelephone())!=null){
                return respBean.error("注册失败 账户存在");
            }
            if(iUserInfoService.setUserInfo(user.getTelephone())){
                userMapper.insert(user);
            }else{
                return respBean.error("注册失败");
            }
        }catch (Exception e){
            return respBean.error("注册失败");
        }
        return respBean.success("注册成功");
    }


}
