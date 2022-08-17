package com.example.learningdemo.generics_9;

import com.example.learningdemo.pojo.Student;
import com.example.learningdemo.pojo.Person;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
泛型
一、
    泛型有三种使用方式，分别为：泛型类、泛型接口、泛型方法
    1.泛型类:泛型类型用于类的定义中，被称为泛型类。通过泛型可以完成对一组类的操作对外开放相同的接口。最典型的就是各种容器类，如：List、Set、Map。
    2.泛型接口与泛型类的定义及使用基本相同
    3.泛型方法:泛型类，是在实例化类的时候指明泛型的具体类型；泛型方法，是在调用方法的时候指明泛型的具体类型 。具体详细见genericsMethod和genericsClassMethod
二、
    1.虚拟机中没有泛型，只有普通类和普通方法,所有泛型类的类型参数在编译时都会被擦除,泛型类并没有自己独有的Class类对象。比如并不存在List<String>.class或是List<Integer>.class，而只有List.class。
    2.创建泛型对象时请指明类型，让编译器尽早的做参数检查（Effective Java，第23条：请不要在新代码中使用原生态类型）
    3.不要忽略编译器的警告信息，那意味着潜在的ClassCastException等着你。
    4.静态变量是被泛型类的所有实例所共享的。对于声明为MyClass<T>的类，访问其中的静态变量的方法仍然是 MyClass.myStaticVar。
      不管是通过new MyClass<String>还是new MyClass<Integer>创建的对象，都是共享一个静态变量。
    5.泛型的类型参数不能用在Java异常处理的catch语句中。因为异常处理是由JVM在运行时刻来进行的。
      由于类型信息被擦除，JVM是无法区分两个异常类型MyException<String>和MyException<Integer>的。对于JVM来说，它们都是 MyException类型的。也就无法执行与异常对应的catch语句。
三、
    E - Element (在集合中使用，因为集合中存放的是元素)
    T - Type（Java 类）
    K - Key（键）
    V - Value（值）
    N - Number（数值类型）
    ？ - 表示不确定的java类型（无限制通配符类型）
四、
    限定通配符对类型进⾏限制， 泛型中有两种限定通配符：
    表示类型的上界，格式为：<？ extends T>，即类型必须为T类型或者T子类 表示类型的下界，格式为：<？ super T>，即类型必须为T类型或者T的父类
    泛型类型必须⽤限定内的类型来进⾏初始化，否则会导致编译错误。
    ⾮限定通配符表⽰可以⽤任意泛型类型来替代，类型为<T>
*/
public class generics {
    public static void main(String[] args) {
        //泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
        //传入的实参类型需与泛型的类型参数类型相同，即为String.
        Generic generic = new Generic("a");
        //传入的实参类型需与泛型的类型参数类型相同，即为Integer.
        Generic<Integer> generic2 = new Generic<>(1);
        Generic generic4 = new Generic(false);
        System.out.println(generic.getKey());
        System.out.println(generic2.getKey());
        System.out.println(generic4.getKey());

        List<Person> personList=new ArrayList<>();
        personList.add(new Person("小红",1));
        personList.add(new Person("小明",2));
        personList.add(new Person("小白",3));
        List<Student>studentList=new ArrayList<>();
        Generic.copyProperty(personList,studentList,Student.class);
        System.out.println(studentList);


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

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //泛型接口
    public interface Generator<T> {
        public T next();
    }
        /**
         * 未传入泛型实参时，与泛型类的定义相同，在声明类的时候，需将泛型的声明也一起加到类中
         * 即：class FruitGenerator<T> implements Generator<T>{
         * 如果不声明泛型，如：class FruitGenerator implements Generator<T>，编译器会报错："Unknown class"
         */
    class FruitGenerator<T> implements Generator<T>{
            @Override
            public T next() {
                return null;
            }
    }
        /**
         * 传入泛型实参时：
         * 定义一个生产器实现这个接口,虽然我们只创建了一个泛型接口Generator<T>
         * 但是我们可以为T传入无数个实参，形成无数种类型的Generator接口。
         * 在实现类实现泛型接口时，如已将泛型类型传入实参类型，则所有使用泛型的地方都要替换成传入的实参类型
         * 即：Generator<T>，public T next();中的的T都要替换成传入的String类型。
         */
    public class FruitGenerators implements Generator<String> {

            private String[] fruits = new String[]{"Apple", "Banana", "Pear"};

            @Override
            public String next() {
                Random rand = new Random();
                return fruits[rand.nextInt(3)];
            }
        }
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // 泛型方法
        /**
         * 泛型方法的基本介绍
         * @param tClass 传入的泛型实参
         * @return T 返回值为T类型
         * 说明：
         *     1）public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
         *     2）只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
         *     3）<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
         *     4）与泛型类的定义一样，此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型。
         */
        // 此处为反射创建对象
        public <T> T genericMethod(Class<T> tClass)throws InstantiationException , IllegalAccessException{
            T instance = tClass.newInstance();
            return instance;
        }
        
        /***
         * @Author sxc
         * @Description 集合属性拷贝u
         * @Date  2022/8/17
         * @Return void
         */
        static <T,K>void copyProperty(List<K> sourceList, List<T> targetList,Class<T> targetClass){
            for (K k : sourceList) {
                try {
                    T a=targetClass.newInstance();
                    BeanUtils.copyProperties(k,a);
                    targetList.add(a);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------
public static class StaticGenerator<T> {
    /**
     * 如果在类中定义使用泛型的静态方法，需要添加额外的泛型声明（将这个方法定义成泛型方法）
     * 即使静态方法要使用泛型类中已经声明过的泛型也不可以。
     * 如：public static void show(T t){..},此时编译器会提示错误信息：
     "StaticGenerator cannot be refrenced from static context"
     */
    public static <T> void show(T t){

    }
}

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        //T与?的区别
        //无界通配符“<?>”主要用于使用泛型类或泛型方法。无法确定参数类型所以无法进行存入操作只能获取
        public void sso(List<?> list){
            list.get(0);
            //list.add("1");
        }
        //类型参数“<T>”主要用于声明泛型类或泛型方法。因此再声明确定的类型后可以进行存取操作
        public void tso(List<T> list,T t){
            list.add(t);
        }

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------


    // 泛型带来的重载问题
    // 两个重载的函数，因为他们的参数类型不同，一个是List<String>另一个是List<Integer> ，但是，这段代码是编译通不过的。
    // 因为参数List<Integer>和List<String>编译之后都被擦除了，变成了一样的原生类型List，擦除动作导致这两个方法的特征签名变得一模一样。
    public class GenericTypes {

/*        public static void method(List<String> list) {
            System.out.println("invoke method(List<String> list)");
        }

        public static void method(List<Integer> list) {
            System.out.println("invoke method(List<Integer> list)");
        }*/
    }


}