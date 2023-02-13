package com.goodhang.service.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 微博表
 * </p>
 *
 * @author jobob
 * @since 2022-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("blogger")
@ApiModel(value="Blogger对象", description="微博表")
public class Blogger implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @ApiModelProperty(value = "微博内容")
    @TableField("blogger_text")
    private String bloggerText;

    @ApiModelProperty(value = "用户详情id")
    @TableField("user_info_id")
    private Integer userInfoId;

    /**
     * 创建时间 如果是LocalDateTime类型 要加@DateTimeFormat不加这个注解查询的时候会报错
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Version
    private Integer version;


}
