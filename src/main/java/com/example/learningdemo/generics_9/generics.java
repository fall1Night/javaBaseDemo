package com.example.learningdemo.generics_9;

/*
泛型
一、
    泛型有三种使用方式，分别为：泛型类、泛型接口、泛型方法
    1.泛型类:泛型类型用于类的定义中，被称为泛型类。通过泛型可以完成对一组类的操作对外开放相同的接口。最典型的就是各种容器类，如：List、Set、Map。
*/
public class generics {
    public static void main(String[] args) {
        //泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
        //传入的实参类型需与泛型的类型参数类型相同，即为Integer.
        Generic generic = new Generic("a");
        //传入的实参类型需与泛型的类型参数类型相同，即为String.
        Generic<Integer> generic2 = new Generic<>(1);
        Generic generic4 = new Generic(1.11);
        System.out.println(generic.getKey());
        System.out.println(generic2.getKey());
        System.out.println(generic4.getKey());
    }


}

// 泛型类
class Generic<T> {
    //key这个成员变量的类型为T,T的类型由外部指定
    private T key;

    public Generic(T key) { //泛型构造方法形参key的类型也为T，T的类型由外部指定
        this.key = key;
    }

    public T getKey() { //泛型方法getKey的返回值类型为T，T的类型由外部指定
        return key;
    }
}