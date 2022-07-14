package com.example.learningdemo.reflection_7.reflectionDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author sxc
 * @Description 使用ApplicationContext的getBeansOfType实现接口实现类的动态调用
 * @Date  2022/7/13
 */
@RestController
@RequestMapping("reflectDemoTest")
public class reflectDemoTest {

    @Autowired
     private PublishVideService publishVideService;

    @GetMapping("test")
    public String test() {
//        PublishVideService publishVideServiceByChannel = publishVideService.getPublishService(ChannelEnum.TikTok.getCode());
        PublishVideService publishVideServiceByChannel = publishVideService.getPublishService(ChannelEnum.BiliBili.getCode());
        return publishVideServiceByChannel.publish();
    }
    @GetMapping("test1")
    public String test1() {
        return "11111";




    }




}
