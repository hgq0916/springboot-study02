package com.ruigu.springboot.study.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hugangquan
 * @date 2020/09/30 10:58
 */
@Data
@Component
@ConfigurationProperties(prefix = "person")
public class PersonProperties {

    List<String> address;

}
