package com.example.learningdemo.reflection_7.reflection;
import com.example.learningdemo.pojo.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 反射
public class reflection {

    public static void main(String[] args) {
//  1、获得 Class 对象
//      (1) 使用 Class 类的 forName 静态方法:
        try {
            Class student =Class.forName("com.example.learningdemo.pojo.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//      (2) 直接获取某一个对象的 class，比如:
        Class<?> student2 = Student.class;
        Class<?> classInt = Integer.TYPE;
//      (3) 调用某个对象的 getClass () 方法，比如:
        Student str = new Student("X",1,"CS");
        Class<?> student3 = str.getClass();


/*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
//        2、判断是否为某个类的实例
//        一般地，我们用 instanceof 关键字来判断是否为某个类的实例。同时我们也可以借助反射中 Class 对象的 isInstance() 方法来判断是否为某个类的实例，它是一个 native 方法：
//        public native boolean isInstance(Object obj);
        student2.isInstance(str);

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
//        3、创建实例
//        通过反射来生成对象主要有两种方式。
//        使用Class对象的newInstance()方法来创建Class对象对应类的实例。
//        Class<?> c = String.class;
//        Object str = c.newInstance();
//        重点!!!!newInstance()方法会调用该类无参构造,若没有则报错InstantiationException
                            try {
                                Object str1 =student3.newInstance();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
//        先通过Class对象获取指定的Constructor对象，再调用Constructor对象的newInstance()方法来创建实例。这种方法可以用指定的构造器构造类的实例。
                            //获取String所对应的Class对象
                            Class<?> c = String.class;
                            //获取String类带一个String参数的构造器
                            Constructor constructor = null;
                            try {
                                constructor = c.getConstructor(String.class);
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            }
        //根据构造器创建实例
                            Object obj = null;
                            try {
                                obj = constructor.newInstance("23333");
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                            System.out.println(obj);

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
//        4、获取方法
//        获取某个Class对象的方法集合，主要有以下几个方法：
//        getDeclaredMethods 方法返回类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。
//                public Method[] getDeclaredMethods() throws SecurityException
//        getMethods 方法返回某个类的所有公用（public）方法，包括其继承类的公用方法。
//                public Method[] getMethods() throws SecurityException
//        getMethod 方法返回一个特定的方法，其中第一个参数为方法名称，后面的参数为方法的参数对应Class的对象。
//                public Method getMethod(String name, Class<?>... parameterTypes)
        System.out.println("开始获取student类中方法----------------------------------------------------------------------------------------------------------");
        Class<?> StudentGetMethod = Student.class;
        try {
            Object student5 = StudentGetMethod.newInstance();
            Method[] methods = StudentGetMethod.getMethods();
            Method[] declaredMethods = StudentGetMethod.getDeclaredMethods();
            //获取methodClass类的add方法
            try {
                Method method = StudentGetMethod.getMethod("add", int.class, int.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            //getMethods()方法获取的所有方法
            System.out.println("getMethods获取的方法：-----------------------------------------------------------------------------------------------------------------");
            for(Method m:methods)
                System.out.println(m);
            //getDeclaredMethods()方法获取的所有方法
            System.out.println("getDeclaredMethods获取的方法：----------------------------------------------------------------------------------------------------------");
            for(Method m:declaredMethods)
                System.out.println(m);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//        5、获取构造器信息
//        获取类构造器的用法与上述获取方法的用法类似。主要是通过Class类的getConstructor方法得到Constructor类的一个实例，而Constructor类有一个newInstance方法可以创建一个对象实例:
//        public T newInstance(Object ... initargs)
//        此方法可以根据传入的参数来调用对应的Constructor创建对象实例。
/*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
//        6、获取类的成员变量（字段）信息
//        getFiled：访问公有的成员变量
        Class<?> StudentGetFiled = Student.class;
        try {
            StudentGetFiled.getField("name");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
//        getDeclaredField：所有已声明的成员变量，但不能得到其父类的成员变量
        try {
            StudentGetFiled.getDeclaredField("major");
            StudentGetFiled.getDeclaredField("name"); //此处报错
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
//        7、调用方法
//        当我们从类中获取了一个方法后，我们就可以用 invoke() 方法来调用这个方法。invoke 方法的原型为:

        try {
            Method getMajor = StudentGetMethod.getMethod("getMajor");
            try {
                Object student=StudentGetMethod.newInstance();
                try {
                    System.out.println("调用getMajor方法---------------------------------------------------------------------------------");
                    // 此处会报wrong number of arguments 暂不清楚问题
                    getMajor.invoke(student,"计算机科学技术");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }



            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        /*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    }


}
