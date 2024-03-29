package com.example.learningdemo.designModel_13.structureType.proxy;

/**
 * @Author sxc
 * @Description 代理模式
 * @Date 2022/09/27
 */
public class proxyTest {
    /**
     * 代理模式:一个类代表另一个类的功能。在代理模式中，我们创建具有现有对象的对象，以便向外界提供功能接口。可以理解为内存中没有这个对象就创建，有就直接返回这个对象。
     * 在直接访问对象时带来的问题，比如说：要访问的对象在远程的机器上。
     * 在面向对象系统中，有些对象由于某些原因（比如对象创建开销很大，或者某些操作需要安全控制，或者需要进程外的访问），
     * 直接访问会给使用者或者系统结构带来很多麻烦，我们可以在访问此对象时加上一个对此对象的访问层。
     * <p>
     * 区别:
     * 1、和适配器模式的区别：适配器模式主要改变所考虑对象的接口，而代理模式不能改变所代理类的接口。
     * 2、和装饰器模式的区别：装饰器模式为了增强功能，而代理模式是为了加以控制。
     * <p>
     * 需求:  以获取磁盘中的图片为例
     */
    public static void main(String[] args) {
        Image image = new ProxyImage("test_10mb.png");
        // 第一次是new的，图像从磁盘加载
        image.display();
        // 第二次取缓存，图像不需要从磁盘加载
        image.display();
    }
}
