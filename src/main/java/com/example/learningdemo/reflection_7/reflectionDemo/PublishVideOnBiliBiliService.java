package com.example.learningdemo.reflection_7.reflectionDemo;

import org.springframework.stereotype.Service;

@Service("publishVideOnBiliBiliService")
public class PublishVideOnBiliBiliService extends PublishVideService {


    @Override
    public ChannelEnum getChannel() {
        return ChannelEnum.BiliBili;
    }


    @Override
    public String publish() {
        String PublishVide=super.publish();
        PublishVide +=getChannel().getMsg();
        return PublishVide;
    }
}
