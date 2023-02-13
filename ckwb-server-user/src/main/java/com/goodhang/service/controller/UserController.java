package com.goodhang.service.controller;

import com.goodhang.service.pojo.User;
import com.goodhang.service.pojo.enums.RespBean;
import com.goodhang.service.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2022-09-20
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    /*@Resource
    private HttpServletRequest request;*/
    RespBean respBean;

    @PostMapping("/postlogin")
    @ApiOperation(value = "根据账号登录")
    public RespBean postLogin(@RequestBody User user){
        return userService.Login(user);
    }

    @PostMapping("/reg")
    @ApiOperation(value = "根据账号注册")
    public RespBean reg(@RequestBody User user){
        user.setId(null);
        if (user.getTelephone().equals("")||user.getUserPassword().equals("")){
            return respBean.error("电话为空或密码为空");
        }
        return userService.reg(user);
    }
}
