package com.goodhang.service.service.impl;

import cn.hutool.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goodhang.service.pojo.UserInfo;
import com.goodhang.service.mapper.UserInfoMapper;
import com.goodhang.service.pojo.enums.RespBean;
import com.goodhang.service.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户详情表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-09-30
 */

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    RespBean respBean ;

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Resource
    private HttpServletRequest request;

    @Resource
    UserInfoMapper userInfoMapper;
    @Override
    public RespBean getUserInfo() {
        try {
            JWT jwt = JWT.of(request.getHeader(tokenHeader));
            String telephone=jwt.getPayload("telephone").toString();

            return respBean.success("成功",userInfoMapper.selectOne( new QueryWrapper<UserInfo>().eq("telephone",telephone)));
        }catch (Exception e){
            return respBean.error("错误信息");
        }
    }

    @Override
    public int getid() {
        try{
            JWT jwt = JWT.of(request.getHeader(tokenHeader));
            String telephone=jwt.getPayload("telephone").toString();
            return userInfoMapper.selectOne( new QueryWrapper<UserInfo>().eq("telephone",telephone)).getId();
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public boolean setUserInfo(String t) {
        try {
            userInfoMapper.insert(new UserInfo().setTelephone(t).setUserSex(1));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public RespBean UpdateUserInfo(UserInfo userInfo) {
        try {
            JWT jwt = JWT.of(request.getHeader(tokenHeader));
            String telephone = jwt.getPayload("telephone").toString();
            UserInfo userInfo1 = userInfoMapper.selectOne(new QueryWrapper<UserInfo>().eq("telephone", telephone));
            userInfo1.setUserName(userInfo.getUserName());
            userInfo1.setUserSex(userInfo.getUserSex());
            userInfoMapper.updateById(userInfo1);
            return respBean.success("修改成功");
        }catch (Exception e){
            return respBean.error("修改失败");
        }
    }


}
