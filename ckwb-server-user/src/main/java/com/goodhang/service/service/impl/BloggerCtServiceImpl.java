package com.goodhang.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.goodhang.service.mapper.UserInfoMapper;
import com.goodhang.service.pojo.BloggerCt;
import com.goodhang.service.mapper.BloggerCtMapper;
import com.goodhang.service.pojo.UserInfo;
import com.goodhang.service.pojo.enums.RespBean;
import com.goodhang.service.pojo.vo.BloggerCtVo;
import com.goodhang.service.service.IBloggerCtService;
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

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-10-29
 */
@Service
public class BloggerCtServiceImpl extends ServiceImpl<BloggerCtMapper, BloggerCt> implements IBloggerCtService {

    RespBean respBean ;
    @Resource
    BloggerCtMapper bloggerCtMapper;
    @Resource
    UserInfoMapper userInfoMapper;
    @Resource
    IUserInfoService iUserInfoService;

    @Override
    public RespBean insertBloggerCt(BloggerCt bloggerCt) {
        try{
            bloggerCt.setId(null);
            bloggerCt.setUserInfoId(iUserInfoService.getid());
            bloggerCtMapper.insert(bloggerCt);
            return respBean.success("评论成功");
        }catch (Exception e){
            return respBean.error("评论失败");
        }
    }

    @Override
    public RespBean deleteBloggerCt(BloggerCt bloggerCt) {

        return null;
    }

    @Override
    public RespBean selectBloggerCt(BloggerCt bloggerCt) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("blogger_id", bloggerCt.getBloggerId());
            queryWrapper.orderByDesc("id");
            List<BloggerCt> bloggerCts = bloggerCtMapper.selectList(queryWrapper);
            List<BloggerCtVo> bloggerCtVos = bloggerCts.stream().map( e -> cvzhc(e)).collect(toList());
            bloggerCtVos = addUserName(bloggerCtVos);
            return respBean.success("成功",bloggerCtVos);
        }catch (Exception e){
            return respBean.error("失败");
        }
    }

    private List<BloggerCtVo> addUserName(List<BloggerCtVo> bloggerCtVos){
        try {
            Set<Integer> userInfoIds = bloggerCtVos.stream().map(BloggerCtVo::getUserInfoId).collect(Collectors.toSet());
            List<UserInfo> userInfos = userInfoMapper.selectList(Wrappers.lambdaQuery(UserInfo.class).in(UserInfo::getId, userInfoIds));
            Map<Integer, String> hashMap = userInfos.stream().collect(HashMap::new,(m, v)->m.put(v.getId(),v.getUserName()),HashMap::putAll);
            bloggerCtVos.forEach(e -> e.setUserName(hashMap.get(e.getUserInfoId())));
            return bloggerCtVos;
        }catch (Exception e){
            System.out.println("系统提示 ： "+e);
            return null;
        }
    }

    private BloggerCtVo cvzhc(BloggerCt bloggerCt){
        BloggerCtVo bloggerCtVo = new BloggerCtVo();
        BeanUtils.copyProperties(bloggerCt , bloggerCtVo);
        return bloggerCtVo;
    }
}
