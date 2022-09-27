package com.example.learningdemo.designModel_13.structureType.bridge;

/**
 * @Author sxc
 * @Description 桥接模式
 * @Date 2022/09/27
 */
public class bridgeTest {
    /**
     * 将抽象部分与实现部分分离，使它们都可以独立的变化。
     * 在有多种可能会变化的情况下，用继承会造成类爆炸问题，扩展起来不灵活。
     * <p>
     * 需求:我们有一个作为桥接实现的 DrawAPI 接口和实现了 DrawAPI 接口的实体类 RedCircle、GreenCircle。
     * Shape 是一个抽象类，将使用 DrawAPI 的对象。bridgeTest 类使用 Shape 类来画出不同颜色的圆。
     */
    public static void main(String[] args) {
        Shape redCircle = new Circle(100, 100, 10, new RedCircle());
        Shape greenCircle = new Circle(100, 100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}
