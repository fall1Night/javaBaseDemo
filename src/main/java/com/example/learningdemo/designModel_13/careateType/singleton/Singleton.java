package com.example.learningdemo.designModel_13.careateType.singleton;

/**
 * @Author sxc
 * @Description 单例模式
 * @Date 2022/09/26
 */
public class Singleton {
    /**
     * 单例模式是设计模式中最常见也最简单的一种设计模式，保证了在程序中只有一个实例存在并且能全局的访问到。
     * (1)不允许其他程序用new对象。
     * 因为new就是开辟新的空间，在这里更改数据只是更改的所创建的对象的数据，如果可以new的话，每一次new都产生一个对象，这样肯定保证不了对象的唯一性。
     * <p>
     * (2)在该类中创建对象
     * 因为不允许其他程序new对象，所以这里的对象需要在本类中new出来
     * <p>
     * (3)对外提供一个可以让其他程序获取该对象的方法
     * 因为对象是在本类中创建的，所以需要提供一个方法让其它的类获取这个对象。
     * <p>
     * 解决问题:在一些常用的工具类、线程池、缓存，数据库，账户登录系统、配置文件等程序中可能只允许我们创建一个对象，
     * 一方面如果创建多个对象可能引起程序的错误，另一方面创建多个对象也造成资源的浪费。
     * <p>
     * 写法:①懒汉式②饿汉式③双重校验锁④静态内部类⑤枚举
     */

    //饿汉
    SingletonEHan singletonEHan = SingletonEHan.getInstance();

    //懒汉(双重校验)
    SingletonLanHan singletonLanHan = SingletonLanHan.getSingletonLanHanFour();

    //静态内部类
    SingletonIn singletonIn = SingletonIn.getSingletonIn();

    //枚举
//    SingletonEnum.instance.whateverMethod();
}
