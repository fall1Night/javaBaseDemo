package com.example.learningdemo.reflection_7.springIOCbyRefluction;

/*
    反射与工厂模式实现IOC
 */
public class springIocByreFlection {
    // 创建工厂模式
    static class Factory{
        public static fruit getInstance(String ClassName){
            fruit f=null;
            try{
                //通过class.newInstance创建对象
                f=(fruit)Class.forName(ClassName).newInstance();
            }catch (Exception e) {
                e.printStackTrace();
            }
            return f;
        }
    }
    static class hello{
        public static void main(String[] a){
            // 传入全限定类名 创建对象
            fruit f=Factory.getInstance("com.example.learningdemo.reflection_7.springIOCbyRefluction.Apple");
            if(f!=null){
                f.eat();
            }
        }
    }

}
