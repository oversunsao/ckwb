package com.goodhang.service.controller;


import com.goodhang.service.pojo.User;
import com.goodhang.service.pojo.UserInfo;
import com.goodhang.service.pojo.enums.RespBean;
import com.goodhang.service.service.IUserInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户详情表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2022-09-30
 */
@RestController
@RequestMapping("/userinfo")
public class UserInfoController {
    @Resource
    private IUserInfoService iUserInfoService;

    @PostMapping("/PostUserInfo")
    @ApiOperation(value = "根据token返回个人详情")
    public RespBean getUserInfo(){
        return iUserInfoService.getUserInfo();
    }

    @PostMapping("/UpdateUserInfo")
    @ApiOperation(value = "根据token修改个人详情")
    public RespBean UpdateUserInfo(@RequestBody UserInfo userInfo){
        return iUserInfoService.UpdateUserInfo(userInfo);
    }
}
