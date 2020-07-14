package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum implements BaseEnum<String> {
    DISABLE("0","禁用"),
    ENABLE("1","启用");
    private final String value; //数据库存储字段
    private final String desc;  //返回的显示描述

    public final static String DICT_CODE="STUDENT_STATUS";

    @Override
    public String getCode() {
        return DICT_CODE;
    }
}