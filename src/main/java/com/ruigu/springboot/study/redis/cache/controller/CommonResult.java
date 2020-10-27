package com.ruigu.springboot.study.redis.cache.controller;

import com.ruigu.springboot.study.redis.cache.model.User;
import lombok.Data;

@Data
public class CommonResult {

    Boolean success;

    String message;

    Object data;

    public CommonResult(boolean b, String message, Object data) {
        this.success =b;
        this.message = message;
        this.data = data;
    }

    public CommonResult(boolean b, String msg) {
        this.success =b;
        this.message = message;
    }
}
