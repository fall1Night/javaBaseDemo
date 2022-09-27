package com.example.learningdemo.designModel_13.structureType.facade;

import com.example.learningdemo.designModel_13.structureType.facade.complexSystem.*;

/**
 * 家庭影院
 * 复杂系统统一对外接口(外观模式)
 */
public class HomeTheaterFacade {

    private Computer computer;
    private Light light;
    private Player player;
    private PopcornPopper popcornPopper;
    private Projector projector;

    public HomeTheaterFacade(Computer computer, Light light, Player player, PopcornPopper popcornPopper, Projector projector) {
        this.computer = computer;
        this.light = light;
        this.player = player;
        this.popcornPopper = popcornPopper;
        this.projector = projector;
    }

    /**
     * 一键观影
     */
    public void watchMovie() {
        computer.on();
        light.down();
        popcornPopper.on();
        popcornPopper.makePopcorn();
        projector.on();
        projector.open();
        player.on();
        player.make3DListener();
    }

    /**
     * 一键关闭
     */
    public void stopMovie() {
        computer.off();
        light.up();
        player.off();
        popcornPopper.off();
        projector.close();
        projector.off();
    }
}