package com.ruigu.springboot.study;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.ruigu.springboot.study.aop.EnablePermession;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.Duration;

    /**
     * @author hugangquan
     * @date 2020/09/09 15:46
     */
// 启用缓存配置
@EnablePermession
@EnableSwagger2
@EnableKnife4j
@EnableCaching
@SpringBootApplication
@EnableAsync
@MapperScan(basePackages = "com.ruigu.springboot.study.redis.cache.mapper")
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class,args);
    }

    /*@Bean
    public ConcurrentMapCacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
        //cacheManager.setStoreByValue(true); //true表示缓存一份副本，否则缓存引用
        return cacheManager;
    }*/

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(60));
                //.disableCachingNullValues();

        return RedisCacheManager.builder(factory)
                .cacheDefaults(config)
                .transactionAware()
                .build();
    }

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);

        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        // 将Key和其散列表数据类型的filed都修改为使用StringRedisSerializer进行序列化
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);

        return redisTemplate;
    }

}
