package com.example.learningdemo.Enum_4;

import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;

/*
 *   枚举
 *   一.
 *      枚举类的本质为public final class T extends Enum定义了一个final类所以枚举不能被继承
 *   二.
 *      枚举是线程安全的,通过枚举实现的单例模式也是线程安全的
 *      有反编译后的枚举代码可以看出枚举都是用static定义的
 *      当一个Java类第一次被真正使用到的时候静态资源被初始化、Java类的加载和初始化过程都是线程安全的（因为虚拟机在加载枚举的类的时候，
 *      会使用ClassLoader的loadClass方法，而这个方法使用同步代码块保证了线程安全）。所以，创建一个enum类型是线程安全的。
 *      也就是说，我们定义的一个枚举，在第一次被真正用到的时候，会被虚拟机加载并初始化，而这个初始化过程是线程安全的。
 *      而我们知道，解决单例的并发问题，主要解决的就是初始化过程中的线程安全问题。
 *      所以，由于枚举的以上特性，枚举实现的单例是天生线程安全的。
 * */
public enum FruitEnum {

    Apple(1, "Apple"),
    Banana(2, "Banana"),
    Orange(3, "Orange");

    private Integer code;
    private String msg;

    // 通过Code获取其枚举的信息
    public static String getMsgByCode(Integer code) {
        if (code == null) {
            return null;
        } else {
            for (FruitEnum fruitEnum : FruitEnum.values()) {
                if (fruitEnum.getCode().equals(code)) {
                    return fruitEnum.getMsg();
                }
            }
        }
        return null;
    }

    // 通过msg获取其枚举的值
    public static Integer getCodeByMsg(String msg) {
        if (ObjectUtils.isEmpty(msg)) {
            return null;
        } else {
            for (FruitEnum fruitEnum : FruitEnum.values()) {
                if (fruitEnum.getMsg().equals(msg)) {
                    return fruitEnum.getCode();
                }
            }
        }
        return null;

    }



    // 可以通过myFruit判断是否符合myFruit的类型
    public static List<Integer> myFruit() {
        return Arrays.asList(FruitEnum.Apple.getCode(), FruitEnum.Banana.getCode());
    }

    FruitEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/

//双重锁校验实现线程安全的单例模式,并且无法解决反序列化会破坏单例的问题。详见   https://www.hollischuang.com/archives/1144
class Singleton {
    private volatile static Singleton singleton;

    private Singleton() {
    }

    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}

// 通过枚举实现的单例模式,比上面的简洁
enum SingletonByEnum {
    INSTANCE;

    public void whateverMethod() {
    }
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/*
// 此处定义枚举类后
public enum T {
    SPRING, SUMMER, AUTUMN, WINTER;
}
//此处为反编译定义的枚举类  当我们使用enum来定义一个枚举类型的时候，编译器会自动帮我们创建一个final类型的类继承Enum类,java.lang.Enum类。这是一个抽象类
public final class T extends Enum {
    private T(String s, int i) {
        super(s, i);
    }

    public static T[] values() {
        T at[];
        int i;
        T at1[];
        System.arraycopy(at = ENUM$VALUES, 0, at1 = new T[i = at.length], 0, i);
        return at1;
    }

    public static T valueOf(String s) {
        return (T) Enum.valueOf(demo / T, s);
    }

    public static final T SPRING;
    public static final T SUMMER;
    private static final T ENUM$VALUES[];

    static {
        SPRING = new T("SPRING", 0);
        SUMMER = new T("SUMMER", 1);
        ENUM$VALUES = (new T[]{
                SPRING, SUMMER
        });
    }
}


// 这个为Enum抽象类
package java.lang;

public abstract class Enum<E extends Enum<E>> implements Constable, Comparable<E>, Serializable {
    private final String name;
    private final int ordinal;

}

*/
