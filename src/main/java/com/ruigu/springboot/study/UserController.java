package com.ruigu.springboot.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author hugangquan
 * @date 2020/09/09 16:06
 */
@RestController
public class UserController {

    @Autowired
    private MyTask myTask;
    @Autowired
    private CacheDemoService cacheDemoService;
    @Autowired
    private CacheManager cacheManager;

    @RequestMapping("/testSync")
    public Object testSync(){
        Future<String> stringFuture = myTask.executeTask();
        try {
            String s = stringFuture.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @RequestMapping("/testSync2")
    public Object testSync2(){
        myTask.executeTask2();
        return "ok";
    }

    @RequestMapping("/testCache")
    public Object testCache(Integer id){
        Cache cache = cacheManager.getCache("user");
        String idx = cache.get(1, String.class);
        System.out.println(idx);
        return cacheDemoService.getFromDB(id);
    }

}
