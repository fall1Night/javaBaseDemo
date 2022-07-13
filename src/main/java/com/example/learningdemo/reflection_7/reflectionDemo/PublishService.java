package com.example.learningdemo.reflection_7.reflectionDemo;

/**
 * @Author sxc
 * @Description 发布接口
 * @Date  2022/7/13
 */
public interface PublishService {

    /**
     * @Author sxc
     * @Description 获取发布渠道
     * @Date  2022/7/13
     * @Return ChannelEnum
     */
    ChannelEnum getChannel();

    /**
     * @Author sxc
     * @Description 根据渠道找到对应渠道发布实现类
     * @Date  2022/7/13
     * @Return PublishService
     */
    PublishService getPublishService(Integer code);

    /**
     * @Author sxc
     * @Description 发布
     * @Date  2022/7/13
     * @Return java.lang.String
     */
    String publish();

}
