package com.ruigu.springboot.study.redis.cache.service;

import com.ruigu.springboot.study.redis.cache.model.User;

import java.util.List;

public interface UserCacheService {
    User getUser(Long id);
    User insertUser(User user);
    User updateUserName(Long id, String userName);
    List<User> findUsers(String userName);
    int deleteUser(Long id);
}
