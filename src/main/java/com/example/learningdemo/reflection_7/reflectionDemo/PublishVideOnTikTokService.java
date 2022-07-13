package com.example.learningdemo.reflection_7.reflectionDemo;

import org.springframework.stereotype.Service;

@Service("publishVideOnTikTokService")
public class PublishVideOnTikTokService extends PublishVideService {


    @Override
    public ChannelEnum getChannel() {
        return ChannelEnum.TikTok;
    }


    @Override
    public String publish() {
        String PublishVide=super.publish();
        PublishVide +=getChannel().getMsg();
        return PublishVide;
    }
}
