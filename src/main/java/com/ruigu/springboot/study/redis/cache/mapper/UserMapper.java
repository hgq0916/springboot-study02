package com.ruigu.springboot.study.redis.cache.mapper;

import com.ruigu.springboot.study.redis.cache.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    User getUserById(Long id);

    int insertUser(User user);

    int updateUserById(User user);

    int deleteUser(Long id);

    @Select("select * from t_user where user_name = #{userName}")
    List<User> getUsersByName(@Param("userName") String userName);
}
