package com.goodhang.service.service;

import com.goodhang.service.pojo.Blogger;
import com.baomidou.mybatisplus.extension.service.IService;
import com.goodhang.service.pojo.enums.RespBean;

/**
 * <p>
 * 微博表 服务类
 * </p>
 *
 * @author jobob
 * @since 2022-10-18
 */
public interface IBloggerService extends IService<Blogger> {
    //发布微博
    RespBean insertBlogger(Blogger blogger);
    //修改微博
    RespBean updateBlogger(Blogger blogger);
    //删除微博
    RespBean deleteBlogger(int id);

    //按发布时间查询所有微博
    RespBean selectBlogger();
    //按发布时间查询自己的微博
    RespBean selectMyBlogger();
}
