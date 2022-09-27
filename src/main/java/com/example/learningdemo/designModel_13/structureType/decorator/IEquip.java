package com.example.learningdemo.designModel_13.structureType.decorator;

/**
 * @Author sxc
 * @Description 装备超类
 * @Date 2022/09/27
 */
public interface IEquip {

    /**
     * 计算攻击力
     *
     * @return
     */
    public int caculateAttack();

    /**
     * 装备的描述
     *
     * @return
     */
    public String description();
}