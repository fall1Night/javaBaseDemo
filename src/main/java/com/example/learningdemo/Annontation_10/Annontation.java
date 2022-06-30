package com.example.learningdemo.Annontation_10;

import org.springframework.web.bind.annotation.GetMapping;

import java.lang.annotation.*;

/*
* 注解
* 一、
*   注解本质是一个继承了Annotation 的特殊接口，其具体实现类是Java 运行时生成的动态代理类。
*   而我们通过反射获取注解时，返回的是Java 运行时生成的动态代理对象$Proxy1。通过代理对象调用自定义注解（接口）的方法，会最终调用AnnotationInvocationHandler 的invoke 方法。
*   该方法会从memberValues 这个Map 中索引出对应的值。而memberValues 的来源是Java 常量池。
*
* 二、元注解
*   @Documented – 注解是否将包含在JavaDoc中
*       ●   一个简单的Annotations 标记注解，表示是否将注解信息添加在java 文档中。
    @Retention – 什么时候使用该注解
        ●   RetentionPolicy.SOURCE : 在编译阶段丢弃。这些注解在编译结束之后就不再有任何意义，所以它们不会写入字节码。@Override, @SuppressWarnings都属于这类注解。
        ●   RetentionPolicy.CLASS : 在类加载的时候丢弃。在字节码文件的处理中有用。注解默认使用这种方式
        ●   RetentionPolicy.RUNTIME : 始终不会丢弃，运行期也保留该注解，因此可以使用反射机制读取该注解的信息。我们自定义的注解通常使用这种方式。
    @Target – 注解用于什么地方
    *   ● ElementType.CONSTRUCTOR: 用于描述构造器
        ● ElementType.FIELD: 成员变量、对象、属性（包括enum实例）
        ● ElementType.LOCAL_VARIABLE: 用于描述局部变量
        ● ElementType.METHOD: 用于描述方法
        ● ElementType.PACKAGE: 用于描述包
        ● ElementType.PARAMETER: 用于描述参数
        ● ElementType.TYPE: 用于描述类、接口(包括注解类型) 或enum声明
    @Inherited – 是否允许子类继承该注解
        ● @Inherited 元注解是一个标记注解，@Inherited 阐述了某个被标注的类型是被继承的。
        * 如果一个使用了@Inherited 修饰的annotation 类型被用于一个class，则这个annotation 将被用于该class 的子类。
        *
        *
         比如该注解@GetMapping的元注解为以下三个
            @Target({ElementType.METHOD})  ---注解用于描述方法
            @Retention(RetentionPolicy.RUNTIME) ---注解为运行期也保留
            @Documented ---注解信息添加在java 文档中。

* */

public class Annontation {

    public static void main(String[] args) {
        // 测试自定义注解
        FruitInfoUtil.getFruitInfo(Apple.class);

        System.out.println(FruitInfoUtil.createFruit(new Fruit()).toString());
    }
}
