package com.goodhang.service.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    private long code;
    private String message;
    private Object data;

    //成功 message
    public static RespBean success(String message){
        return new RespBean(200,message,null);
    }
    //成功 message obj
    public static RespBean success(String message, Object data){
        return new RespBean(200,message,data);
    }
    //失败 message
    public static RespBean error(String message){
        return new RespBean(500,message,null);
    }
    //失败 message obj
    public static RespBean error(String message, Object data){
        return new RespBean(500,message,data);
    }
}