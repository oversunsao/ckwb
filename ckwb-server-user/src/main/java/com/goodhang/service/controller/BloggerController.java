package com.goodhang.service.controller;


import com.goodhang.service.pojo.Blogger;
import com.goodhang.service.pojo.enums.RespBean;
import com.goodhang.service.service.IBloggerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 微博表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2022-10-18
 */
@RestController
@RequestMapping("/blogger")
public class BloggerController {

    @Resource
    IBloggerService iBloggerService;
    RespBean respBean;

    @PostMapping("publish")
    @ApiOperation(value = "发布微博")
    public RespBean insertBlogger(@RequestBody Blogger blogger){
        return iBloggerService.insertBlogger(blogger);
    }

    @PostMapping("update")
    @ApiOperation(value = "修改微博")
    public RespBean updateBlogger(@RequestBody Blogger blogger){
        return iBloggerService.updateBlogger(blogger);
    }
    @PostMapping("delete")
    @ApiOperation(value = "删除微博")
    public RespBean deleteBlogger(@RequestBody Blogger blogger){
        return iBloggerService.deleteBlogger(blogger.getId());
    }
    @PostMapping("select")
    @ApiOperation(value = "查询所有微博")
    public RespBean setectBlogger(){
        return iBloggerService.selectBlogger();
    }
    @PostMapping("selectMy")
    @ApiOperation(value = "查询我的微博")
    public RespBean setectMyBlogger(){
        return iBloggerService.selectMyBlogger();
    }
}
