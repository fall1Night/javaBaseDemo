package com.example.learningdemo.reflection_7.reflectionDemo;


import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("publishVideService")
public class PublishVideService implements PublishService {
    private String PublishVide;

    public static ApplicationContext applicationContext=null;

    @Override
    public ChannelEnum getChannel() {
        return ChannelEnum.defaultChannel;
    }


    @Override
    public PublishVideService getPublishService(Integer code) {
        Map<String, PublishVideService> PublishServiceVideServiceMap=ApplicationContextUtils.applicationContext.getBeansOfType(PublishVideService.class);
        for (PublishVideService publishVideService : PublishServiceVideServiceMap.values()) {
            if (publishVideService.getChannel().getCode().equals(code)){
                return publishVideService;
            }
        }
        return null;
    }

    @Override
    public String publish() {
       String PublishVide= new String();
        PublishVide="发布视频";
       return PublishVide;
    }
}
