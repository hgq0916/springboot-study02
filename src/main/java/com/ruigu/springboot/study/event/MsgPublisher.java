package com.ruigu.springboot.study.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MsgPublisher {

    @Autowired
    private ApplicationContext applicationContext;

    public void publishMsg(String str){
        applicationContext.publishEvent(new Msg<String>(str));
    }

}
