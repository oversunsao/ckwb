package com.goodhang.service.pojo.enums;

import lombok.Getter;

@Getter
public enum ResponseEnum {

    SUCCESS(0, "成功"),

    ;

    Integer code;

    String desc;

    ResponseEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}