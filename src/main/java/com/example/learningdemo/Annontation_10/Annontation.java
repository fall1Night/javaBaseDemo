package com.example.learningdemo.Annontation_10;

/*
* 注解
* 一、
*   注解本质是一个继承了Annotation 的特殊接口，其具体实现类是Java 运行时生成的动态代理类。
*   而我们通过反射获取注解时，返回的是Java 运行时生成的动态代理对象$Proxy1。通过代理对象调用自定义注解（接口）的方法，会最终调用AnnotationInvocationHandler 的invoke 方法。
*   该方法会从memberValues 这个Map 中索引出对应的值。而memberValues 的来源是Java 常量池。
* */
public class Annontation {
}
