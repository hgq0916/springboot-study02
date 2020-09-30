package com.ruigu.springboot.study.lombok;

import lombok.Synchronized;

/**
 * @author hugangquan
 * @date 2020/09/17 15:22
 */
public class TestSynchronized {

    private Object haha = new Object();

    @Synchronized("haha")
    public void print(){
        System.out.println("hello");
    }

    @Synchronized("haha")
    public void print2(){
        System.out.println("hello");
    }

}
