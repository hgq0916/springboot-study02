package com.ruigu.springboot.study.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class MsgListener {

    @Async
    @EventListener
    public void reiceveMsg(Msg<String> msg){
        System.out.println("收到消息："+msg.getData());
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("处理消息："+msg.getData());
    }

}
