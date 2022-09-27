package com.example.learningdemo.designModel_13.structureType.decorator;

/**
 * @Author sxc
 * @Description 武器-装备实现类
 * @Date 2022/09/27
 */
public class ArmEquip implements IEquip {

    @Override
    public int caculateAttack() {
        return 1;
    }

    @Override
    public String description() {
        return "屠龙刀";
    }
}
