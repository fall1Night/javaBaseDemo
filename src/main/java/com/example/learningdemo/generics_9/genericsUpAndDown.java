package com.example.learningdemo.generics_9;

import java.util.ArrayList;
import java.util.List;

// 上下界限定符extends 和 super
public class genericsUpAndDown {


    public static void main(String[] args) {
        GenericTest genericTest=new GenericTest();
    }

}
 class Food {}
 class Fruit1 extends Food {}
 class Apple1 extends Fruit {}
 class Banana1 extends Fruit{}

 class GenericTest {

    public void testExtends(List<? extends Fruit1> list){

        //报错,extends为上界通配符,只能取值,不能放.
        //因为Fruit的子类不只有Apple还有Banana,这里不能确定具体的泛型到底是Apple还是Banana，所以放入任何一种类型都会报错
        //list.add(new Apple());
        //list.add(new Fruit1());

        //可以正常获取
        Fruit1 fruit1 = list.get(1);
    }

    public void testSuper(List<? super Fruit1> list){

        //super为下界通配符，可以存放元素，但是也只能存放当前类或者子类的实例，以当前的例子来讲，
        //无法确定Fruit的父类是否只有Food一个(Object是超级父类)
        //因此放入Food的实例编译不通过
//        list.add(new Food());
        list.add(new Fruit1());

        Object object = list.get(1);
    }







}
class GenericA<T extends Number>{
    private T key;

    public GenericA(T key) {
        this.key = key;
    }

    public T getKey(){
        return key;
    }
    public static void showKeyValue1(GenericA<? extends Number> obj){
        obj.getKey();
    }

public static void main(String[] args) {
   // GenericA<String> generic1 = new GenericA<String>("11111");
    GenericA<Integer> generic2 = new GenericA<Integer>(2222);
    GenericA<Float> generic3 = new GenericA<Float>(2.4f);
    GenericA<Double> generic4 = new GenericA<Double>(2.56);

//这一行代码编译器会提示错误，因为String类型并不是Number类型的子类
//showKeyValue1(generic1);
    showKeyValue1(generic2);
    showKeyValue1(generic3);
    showKeyValue1(generic4);
}


}