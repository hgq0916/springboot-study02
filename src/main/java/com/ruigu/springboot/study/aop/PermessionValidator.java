package com.ruigu.springboot.study.aop;

import com.google.common.collect.Sets;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hugangquan
 * @date 2020/09/10 15:03
 */
@Aspect
@Component
public class PermessionValidator {

    private static final Map<Integer, Set<String>> map = new ConcurrentHashMap<>(16);

    static {
        map.put(1, Sets.newHashSet("USER_PERMESSION","ADMIN_PERMESSION"));
    }

    @Before("@annotation(com.ruigu.springboot.study.aop.HasPermession)")
    public void before(JoinPoint joinPoint) throws IllegalAccessException {
        Object[] args = joinPoint.getArgs();
        System.out.println(Arrays.toString(args));
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        HasPermession hasPermession = method.getAnnotation(HasPermession.class);
        String value = hasPermession.value();
        Set<String> permessions = map.get((Integer) args[0]);
        if(!CollectionUtils.isEmpty(permessions)) {
            boolean allMatch = permessions.stream().anyMatch(per -> per.equals(value));
            if(!allMatch) {
                throw new IllegalAccessException("没有权限");
            }
        }else {
            throw new IllegalAccessException("没有权限");
        }
    }

}
