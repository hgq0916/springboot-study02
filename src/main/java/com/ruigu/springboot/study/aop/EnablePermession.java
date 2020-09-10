package com.ruigu.springboot.study.aop;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(PermessionValidator.class)
public @interface EnablePermession {

}
