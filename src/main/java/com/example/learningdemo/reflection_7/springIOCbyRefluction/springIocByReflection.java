package com.example.learningdemo.reflection_7.springIOCbyRefluction;

import java.io.*;
import java.util.Properties;

/*
    反射与工厂模式实现IOC
 */
public class springIocByReflection {
    // 现在就算我们添加任意多个子类的时候，工厂类都不需要修改。
    // 使用反射机制实现的工厂模式可以通过反射取得接口的实例，但是需要传入完整的包和类名。
    // 而且用户也无法知道一个接口有多少个可以使用的子类，所以我们通过属性文件的形式配置所需要的子类。
    // 下面编写使用反射机制并结合属性文件的工厂模式（即IoC）。
    static class  init{
        public static Properties getPro() throws FileNotFoundException, IOException {
            Properties pro=new Properties();
            // 定义配置之类的文件
            File f=new File("fruit.properties");
            //if(f.exists()){
                pro.load(new FileInputStream(f));
           /* }else{
                pro.setProperty("apple", "Reflect.Apple");
                pro.setProperty("orange", "Reflect.Orange");
                pro.store(new FileOutputStream(f), "FRUIT CLASS");
            }*/
            return pro;
        }
    }
    static class Factory{
        public static fruit getInstance(String ClassName){
            fruit f=null;
            try{
                f=(fruit)Class.forName(ClassName).newInstance();
            }catch (Exception e) {
                e.printStackTrace();
            }
            return f;
        }
    }
    static class hello {
        public static void main(String[] a) throws FileNotFoundException, IOException {
            Properties pro = init.getPro();
            fruit f = Factory.getInstance(pro.getProperty("apple"));
            if (f != null) {
                f.eat();
            }
        }
    }
}
