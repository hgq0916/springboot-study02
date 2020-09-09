package com.ruigu.springboot.study;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author hugangquan
 * @date 2020/09/09 17:08
 */
@Service
public class CacheDemoService {

    @Cacheable(cacheNames = "user",key = "#id")
    public Object getFromDB(Integer id){

        System.out.println("从数据库查询...");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "haha"+id;
    }

}
