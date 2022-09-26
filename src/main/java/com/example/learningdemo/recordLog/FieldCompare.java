package com.example.learningdemo.recordLog;

import java.lang.annotation.*;

@Target({ElementType.FIELD})  //用于成员变量、对象、属性（包括enum实例）
@Retention(RetentionPolicy.RUNTIME) //始终不会丢弃，运行期也保留该注解，因此可以使用反射机制读取该注解的信息
@Inherited  //是否允许子类继承该注解
@Documented //将注解信息添加在java 文档中
public @interface FieldCompare {

    //字段名称
    String chineseName();

    //类型映射
    String properties() default "";

}


