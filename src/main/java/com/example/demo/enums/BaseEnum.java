package com.example.demo.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * @Description: 通用枚举转化
 * @Author: eagle
 * @Date: 2020-07-11 22:49
 */

public interface BaseEnum<T extends Serializable> {

    @JsonValue
    T getValue();
    String getCode();
}
