package com.goodhang.service.controller;


import com.goodhang.service.pojo.Blogger;
import com.goodhang.service.pojo.BloggerCt;
import com.goodhang.service.pojo.enums.RespBean;
import com.goodhang.service.service.IBloggerCtService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2022-10-29
 */
@RestController
@RequestMapping("/blogger-ct")
public class BloggerCtController {

    @Resource
    IBloggerCtService iBloggerCtService;

    @PostMapping("publish")
    @ApiOperation(value = "发布微博评论")
    public RespBean insertBlogger(@RequestBody BloggerCt bloggerCt){
        System.out.println(bloggerCt.toString());
        return iBloggerCtService.insertBloggerCt(bloggerCt);
    }

    @PostMapping("select")
    @ApiOperation(value = "查询微博所有评论")
    public RespBean setectBlogger(@RequestBody BloggerCt bloggerCt){
        return iBloggerCtService.selectBloggerCt(bloggerCt);
    }
}
