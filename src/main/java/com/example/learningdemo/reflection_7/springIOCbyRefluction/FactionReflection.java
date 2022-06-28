package com.example.learningdemo.reflection_7.springIOCbyRefluction;

public class FactionReflection {
    // 创建工厂模式 通过工厂容器统一创建对象
    // 局限性是使用反射机制实现的工厂模式可以通过反射取得接口的实例，但是需要传入完整的包和类名。
    // 而且用户也无法知道一个接口有多少个可以使用的子类，所以我们通过属性文件的形式配置所需要的子类。 详见 springIocByReflection类
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
