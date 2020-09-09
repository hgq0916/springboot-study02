package com.ruigu.springboot.study;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author hugangquan
 * @date 2020/09/09 16:24
 */
@Component
public class MyTask {

    @Async
    public Future<String> executeTask(){
        System.out.println("执行任务中...");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>("执行完成") ;
    }

    @Async
    public void executeTask2(){
        System.out.println("执行任务中...");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行完成");
    }

}
