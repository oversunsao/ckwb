package com.goodhang.service.pojo.vo;


import lombok.Data;

@Data
public class BloggerCtVo {
    private Integer Id;

    private Integer bloggerId;
    private Integer userInfoId;
    private String bloggerCtText;

    private String userName;
}
