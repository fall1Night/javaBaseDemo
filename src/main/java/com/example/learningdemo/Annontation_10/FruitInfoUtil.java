package com.example.learningdemo.Annontation_10;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 注解处理器
 */
public class FruitInfoUtil {
    public static void getFruitInfo(Class<?> clazz){

        String strFruitName=" 水果名称：";
        String strFruitColor=" 水果颜色：";
        String strFruitProvicer="供应商信息：";

        // 通过反射获取类中的所有属性
        Field[] fields = clazz.getDeclaredFields();

        for(Field field :fields){
            // 判断属性上是否有FruitName注解,有则返回true
            if(field.isAnnotationPresent(FruitName.class)){
                // 获取注解中的值
                FruitName fruitName = (FruitName) field.getAnnotation(FruitName.class);
                strFruitName=strFruitName+fruitName.value();
                System.out.println(strFruitName);
            }
            else if(field.isAnnotationPresent(FruitColor.class)){
                FruitColor fruitColor= (FruitColor) field.getAnnotation(FruitColor.class);
                strFruitColor=strFruitColor+fruitColor.fruitColor().toString();
                System.out.println(strFruitColor);
            }
            else if(field.isAnnotationPresent(FruitProvider.class)){
                FruitProvider fruitProvider= (FruitProvider) field.getAnnotation(FruitProvider.class);
                strFruitProvicer=" 供应商编号："+fruitProvider.id()+" 供应商名称："+fruitProvider.name()+" 供应商地址："+fruitProvider.address();
                System.out.println(strFruitProvicer);
            }
        }
    }




    // 此处注解与反射结合实现注解赋值
    public static Fruit createFruit(Fruit fruit) {
        try {
            // 创建对象
            Field[] declareFields = fruit.getClass().getDeclaredFields();
            for (Field declareField : declareFields) {
                if (declareField.isAnnotationPresent(FruitName.class)) {
                    // 获取注解中的值
                    try {
                        Method setName = fruit.getClass().getMethod("setName", String.class);
                        try {
                            setName.invoke(fruit, declareField.getAnnotation(FruitName.class).value());
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                } else if (declareField.isAnnotationPresent(FruitColor.class)) {
                    try {
                        Method setColor = fruit.getClass().getMethod("setColor", String.class);
                        try {
                            setColor.invoke(fruit, declareField.getAnnotation(FruitColor.class).toString());
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                } else if (declareField.isAnnotationPresent(FruitProvider.class)) {
                    try {
                        Method setProvider = fruit.getClass().getMethod("setProvider", String.class);
                        try {
                            FruitProvider fruitProvider=declareField.getAnnotation(FruitProvider.class);
                            setProvider.invoke(fruit," 供应商编号："+fruitProvider.id()+" 供应商名称："+fruitProvider.name()+" 供应商地址："+fruitProvider.address() );
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fruit;
    }
}