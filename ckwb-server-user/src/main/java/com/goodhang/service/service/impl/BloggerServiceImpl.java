package com.goodhang.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.goodhang.service.mapper.BloggerCtMapper;
import com.goodhang.service.mapper.UserInfoMapper;
import com.goodhang.service.pojo.Blogger;
import com.goodhang.service.mapper.BloggerMapper;
import com.goodhang.service.pojo.UserInfo;
import com.goodhang.service.pojo.enums.RespBean;
import com.goodhang.service.pojo.vo.BloggerVo;
import com.goodhang.service.service.IBloggerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goodhang.service.service.IUserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * <p>
 * 微博表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-10-18
 */
@Service
public class BloggerServiceImpl extends ServiceImpl<BloggerMapper, Blogger> implements IBloggerService {
    RespBean respBean ;
    @Resource
    BloggerMapper bloggerMapper;
    @Resource
    UserInfoMapper userInfoMapper;
    @Resource
    BloggerCtMapper bloggerCtMapper;
    @Resource
    IUserInfoService iUserInfoService;
    @Override
    public RespBean insertBlogger(Blogger blogger) {
        try{
            blogger.setId(null);
            blogger.setUserInfoId(iUserInfoService.getid());
            bloggerMapper.insert(blogger);
            return respBean.success("发布成功");
        }catch (Exception e){
            return respBean.error("微博发布失败");
        }
    }

    @Override
    public RespBean updateBlogger(Blogger blogger) {
        try{
            Blogger b=bloggerMapper.selectOne(new QueryWrapper<Blogger>().eq("id",blogger.getId()));
            if(b!=null){
                if (b.getUserInfoId()==iUserInfoService.getid()) {
                    b.setBloggerText(blogger.getBloggerText());
                    bloggerMapper.updateById(b);
                    return respBean.success("修改成功");
                }

            }
            return respBean.error("修改失败");
        }catch (Exception e){
            return respBean.error("修改失败");
        }
    }

    @Override
    public RespBean deleteBlogger(int id) {
        try{
            Blogger b=bloggerMapper.selectOne(new QueryWrapper<Blogger>().eq("id",id));
            if(b!=null){
                if (b.getUserInfoId()==iUserInfoService.getid()) {
                    bloggerMapper.deleteById(id);

                    return respBean.success("删除成功");
                }
            }
            return respBean.error("删除失败");
        }catch (Exception e){
            return respBean.error("删除失败");
        }
    }

    @Override
    public RespBean selectBlogger() {
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByDesc("id");
            List<Blogger> bloggers = bloggerMapper.selectList(queryWrapper);
            List<BloggerVo> bloggerVos = bloggers.stream().map(e -> BzhBvo(e)).collect(toList());
            bloggerVos = addUserName(bloggerVos);
            return respBean.success("成功",bloggerVos);
        }catch (Exception e){
            return respBean.error("查询失败");
        }
    }

    @Override
    public RespBean selectMyBlogger() {
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_info_id",iUserInfoService.getid());
            queryWrapper.orderByDesc("id");
            List<Blogger> bloggers = bloggerMapper.selectList(queryWrapper);
            List<BloggerVo> bloggerVos = bloggers.stream().map(e -> BzhBvo(e)).collect(toList());
            bloggerVos = addUserName(bloggerVos);
            return respBean.success("成功",bloggerVos);
        }catch (Exception e){
            return respBean.error("查询失败");
        }
    }
    private List<BloggerVo> addUserName(List<BloggerVo> bloggerVos){
        try {
            Set<Integer> userInfoIds = bloggerVos.stream().map(BloggerVo::getUserInfoId).collect(Collectors.toSet());
            List<UserInfo> userInfos = userInfoMapper.selectList(Wrappers.lambdaQuery(UserInfo.class).in(UserInfo::getId, userInfoIds));
            Map<Integer, String> hashMap = userInfos.stream().collect(HashMap::new,(m, v)->m.put(v.getId(),v.getUserName()),HashMap::putAll);
            bloggerVos.forEach(e -> e.setUserName(hashMap.get(e.getUserInfoId())));
            return bloggerVos;
        }catch (Exception e){
            System.out.println("系统提示 ： "+e);
            return null;
        }
    }

    //转换类
    private BloggerVo BzhBvo(Blogger blogger){
        BloggerVo bloggerVo = new BloggerVo();
        BeanUtils.copyProperties(blogger , bloggerVo);
        return bloggerVo;
    }

}
