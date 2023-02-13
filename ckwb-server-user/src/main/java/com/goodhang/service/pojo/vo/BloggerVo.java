package com.goodhang.service.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class BloggerVo {

    private Integer Id;
    @TableField("blogger_text")
    private String bloggerText;
    @TableField("user_info_id")
    private Integer userInfoId;
    @TableField("user_name")
    private String userName;

}
