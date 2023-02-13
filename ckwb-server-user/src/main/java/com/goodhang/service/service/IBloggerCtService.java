package com.goodhang.service.service;

import com.goodhang.service.pojo.BloggerCt;
import com.baomidou.mybatisplus.extension.service.IService;
import com.goodhang.service.pojo.enums.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2022-10-29
 */
public interface IBloggerCtService extends IService<BloggerCt> {
    //发布微博评论
    RespBean insertBloggerCt(BloggerCt bloggerCt);
    //删除微博评论
    RespBean deleteBloggerCt(BloggerCt bloggerCt);
    //查询所有评论
    RespBean selectBloggerCt(BloggerCt bloggerCt);
}
