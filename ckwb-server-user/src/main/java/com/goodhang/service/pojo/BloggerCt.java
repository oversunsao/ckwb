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
 * 
 * </p>
 *
 * @author jobob
 * @since 2022-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("blogger_ct")
@ApiModel(value="BloggerCt对象", description="")
public class BloggerCt implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @ApiModelProperty(value = "微博id")
    @TableField("blogger_id")
    private Integer bloggerId;

    @ApiModelProperty(value = "用户详情id")
    @TableField("user_info_id")
    private Integer userInfoId;

    @ApiModelProperty(value = "微博评价内容")
    @TableField("blogger_ct_text")
    private String bloggerCtText;

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
