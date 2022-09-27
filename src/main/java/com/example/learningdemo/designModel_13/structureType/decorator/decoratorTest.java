package com.example.learningdemo.designModel_13.structureType.decorator;

/**
 * @Author sxc
 * @Description 装饰者模式
 * @Date 2022/09/27
 */
public class decoratorTest {
    /**
     * 装饰者模式：若要扩展功能，装饰者提供了比集成更有弹性的替代方案，动态地将责任附加到对象上。
     * <p>
     * 一般的，我们为了扩展一个类经常使用继承方式实现，由于继承为类引入静态特征，并且随着扩展功能的增多，子类会很膨胀。
     * 在不想增加很多子类的情况下扩展类。我们需要给这个类添加一些辅助的功能，并且不希望改变这个类的代码
     * 这里还体现了一个原则：类应该对扩展开放，对修改关闭。
     * <p>
     * 需求:现有装备系统,需要添加镶嵌宝石系统,基本要求，要可以计算出每种装备在镶嵌了各种宝石后的攻击力和描述
     */
    public static void main(String[] args) {
        // 一个镶嵌1颗红宝石，1颗蓝宝石的武器
        System.out.println(" 一个镶嵌1颗红宝石，1颗蓝宝石,1颗黄宝石的武器");
        IEquip equip = new RedGemDecorator(new BlueGemDecorator(new YellowGemDecorator(new ArmEquip())));
        System.out.println("攻击力  : " + equip.caculateAttack());
        System.out.println("描述 :" + equip.description());
        System.out.println("-------");

    }
}
