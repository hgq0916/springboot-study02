package com.ruigu.springboot.study;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author hugangquan
 * @date 2020/09/09 16:06
 */
@Api(tags = "用户管理")
@RestController
public class UserController {

    @Autowired
    private MyTask myTask;
    @Autowired
    private CacheDemoService cacheDemoService;
    @Autowired
    private CacheManager cacheManager;

    @ApiOperation(value="测试spring异步调用方法")
    @RequestMapping(value = "/testSync",method = RequestMethod.GET)
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

    @RequestMapping(value = "/testSync2",method = RequestMethod.GET)
    @ApiOperation(value="测试spring异步调用方法2")
    public Object testSync2(){
        myTask.executeTask2();
        return "ok";
    }

    @RequestMapping(value = "/testCache",method = RequestMethod.GET)
    @ApiOperation(value="测试spring缓存")
    public Object testCache(@ApiParam(value = "用户id") Integer id){
        Cache cache = cacheManager.getCache("user");
        String idx = cache.get(1, String.class);
        System.out.println(idx);
        return cacheDemoService.getFromDB(id);
    }

}
