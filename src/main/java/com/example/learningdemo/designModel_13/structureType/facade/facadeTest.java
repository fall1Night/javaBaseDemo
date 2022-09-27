package com.example.learningdemo.designModel_13.structureType.facade;

import com.example.learningdemo.designModel_13.structureType.facade.complexSystem.*;

/**
 * @Author sxc
 * @Description 外观模式
 * @Date 2022/09/27
 */
public class facadeTest {
    /**
     * 定义：提供一个统一的接口，用来访问子系统中的一群接口，外观定义了一个高层的接口，
     * 让子系统更容易使用。其实就是为了方便客户的使用，把一群操作，封装成一个方法。
     * <p>
     * 1.客户端不需要知道系统内部的复杂联系，整个系统只需提供一个"接待员"即可。 2、定义系统的入口。
     * <p>
     * 需求：我比较喜欢看电影，于是买了投影仪、电脑、音响、设计了房间的灯光、买了爆米花机，然后我想看电影的时候，
     * 1、打开爆米花机
     * 2、制作爆米花
     * 3、将灯光调暗
     * 4、打开投影仪
     * 5、放下投影仪投影区
     * 6、打开电脑
     * 7、打开播放器
     * 8、将播放器音调设为环绕立体声
     * ...
     * 需要每个部分都进行操作很繁琐,
     * 所以我需要一键观影和一键关闭。
     */
    public static void main(String[] args) {
        Computer computer = new Computer();
        Light light = new Light();
        Player player = new Player();
        PopcornPopper popcornPopper = new PopcornPopper();
        Projector projector = new Projector();
        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade(computer, light, player, popcornPopper, projector);
        //一键观影
        homeTheaterFacade.watchMovie();
        //一键关闭
        homeTheaterFacade.stopMovie();
    }
}
