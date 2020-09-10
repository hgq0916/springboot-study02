package com.ruigu.springboot.study.aop;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HasPermession {

    String value() default "";

}
