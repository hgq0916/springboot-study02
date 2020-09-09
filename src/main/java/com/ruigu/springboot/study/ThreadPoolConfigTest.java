package com.ruigu.springboot.study;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author hugangquan
 * @date 2020/09/09 15:53
 */
@Configuration
@EnableAsync
public class ThreadPoolConfigTest implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 配置核心线程数
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        // 配置最大线程数
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors()*5);
        // 配置队列容量
        executor.setQueueCapacity(Runtime.getRuntime().availableProcessors()*2);
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 设置线程的名称前缀
        executor.setThreadNamePrefix("myThread-");
        // 初始化线程池
        executor.initialize();

        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
                throwable.printStackTrace();
            }
        };
    }

}
