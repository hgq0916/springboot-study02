package com.ruigu.springboot.study.redis.cache.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Alias(value = "user")
public class User implements Serializable {
    // 开启Spring Redis Cache时，加入序列化
    private static final long serialVersionUID = -4947062488310146862L;

    private Long id;
    @NotNull(message = "用户名不能为空")
    private String userName;
    @NotNull(message = "备注不能为空")
    private String note;
    @NotNull(message = "性别不能为空")
    private SexEnum sex;

    public User(String userName, int sex, String note) {
        this.userName = userName;
        this.sex = SexEnum.values()[sex];
        this.note = note;
    }
}
