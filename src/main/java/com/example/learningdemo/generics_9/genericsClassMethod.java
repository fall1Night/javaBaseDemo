package com.example.learningdemo.generics_9;

public class genericsClassMethod {
        public static void main(String[] args) {
            Apple apple = new Apple();
            Person person = new Person();

            GenerateTest<Fruit> generateTest = new GenerateTest<Fruit>();
            //apple是Fruit的子类，所以这里可以
            generateTest.show_1(apple);
            //编译器会报错，因为泛型类型实参指定的是Fruit，而传入的实参类是Person
            //generateTest.show_1(person);

            //使用这两个方法都可以成功
            generateTest.show_2(apple);
            generateTest.show_2(person);

            //使用这两个方法也都可以成功
            generateTest.show_3(apple);
            generateTest.show_3(person);

            //泛型方法和可变参数
            printMsg("111",222,"aaaa","2323.4",55.55);
        }
        public static <T> void printMsg(T... args){
        for(T t : args){
            //("泛型测试","t is " + t);
        }
    }

    }

class Fruit{
    @Override
    public String toString() {
        return "fruit";
    }
}

class Apple extends Fruit{
    @Override
    public String toString() {
        return "apple";
    }
}

class Person{
    @Override
    public String toString() {
        return "Person";
    }
}

class GenerateTest<T>{
    public void show_1(T t){
        System.out.println(t.toString());
    }

    //在泛型类中声明了一个泛型方法，使用泛型E，这种泛型E可以为任意类型。可以类型与T相同，也可以不同。
    //由于泛型方法在声明的时候会声明泛型<E>，因此即使在泛型类中并未声明泛型，编译器也能够正确识别泛型方法中识别的泛型。
    public <E> void show_3(E t){
        System.out.println(t.toString());
    }

    //在泛型类中声明了一个泛型方法，使用泛型T，注意这个T是一种全新的类型，可以与泛型类中声明的T不是同一种类型。
    public <T> void show_2(T t){
        System.out.println(t.toString());
    }
}