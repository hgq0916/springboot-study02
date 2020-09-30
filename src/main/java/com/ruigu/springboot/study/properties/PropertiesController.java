package com.ruigu.springboot.study.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hugangquan
 * @date 2020/09/30 10:17
 */
@RestController
public class PropertiesController {

  /*  @Value("${person.address}")
    List<String> address;*/

    @Autowired
    private PersonProperties personProperties;

    @RequestMapping("/person/address")
    public Object address(){
        return personProperties.address;
    }

}
