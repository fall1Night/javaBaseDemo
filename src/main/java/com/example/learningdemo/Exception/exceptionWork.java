package com.example.learningdemo.Exception;

/*
异常
一、
1.查性异常：最具代表的检查性异常是用户错误或问题引起的异常，这是程序员无法预见的。
  例如要打开一个不存在文件时，一个异常就发生了，这些异常在编译时不能被简单地忽略。

2.运行时异常： 运行时异常是可能被程序员避免的异常。与检查性异常相反，运行时异常可以在编译时被忽略。

3错误： 错误不是异常，而是脱离程序员控制的问题。错误在代码中通常被忽略。例如，当栈溢出时，一个错误就发生了，它们在编译也检查不到的。

二、
Error和Exception的区别：Error通常是灾难性的致命的错误，是程序无法控制和处理的，当出现这些异常时，
Java虚拟机（JVM）一般会选择终止线程；Exception通常情况下是可以被程序处理的，并且在程序中应该尽可能的去处理这些异常。

三、
　  • try     -- 用于监听。将要被监听的代码(可能抛出异常的代码)放在try语句块之内，当try语句块内发生异常时，异常就被抛出。
　　• catch   -- 用于捕获异常。catch用来捕获try语句块中发生的异常。
　　• finally -- finally语句块总是会被执行。它主要用于回收在try块里打开的物力资源(如数据库连接、网络连接和磁盘文件)。只有finally块，执行完成之后，才会回来执行try或者catch块中的return或者throw语句，如果finally中使用了return或者throw等终止方法的语句，则就不会跳回执行，直接停止。
　　• throw   -- 用于抛出异常。 直接抛出异常如: throw new ArithmeticException();
　　• throws  -- 用在方法签名中，用于声明该方法可能抛出的异常。 在方法体后面添加throws直接抛出异常,提前声明,在调用时需添加捕获异常语句

四、
    finally创建的代码块在try/catch块完成之后另一个try/catch出现之前执行。finally块无论有没有异常抛出都会执行。
    如果抛出异常，即使没有catch子句匹配，finally也会执行。一个方法将从一个try/catch块返回到调用程序的任何时候，
    经过一个未捕获的异常或者是一个明确的返回语句，finally子句在方法返回之前仍将执行。这在关闭文件句柄和释放任何在方法开始时被分配的其他资源是很有用。
    finally子句是可选项，可以有也可以无，但是每个try语句至少需要一个catch或者finally子句。

五、
    - ArithmeticException（算术异常）
    - ClassCastException （类转换异常）
    - IllegalArgumentException （非法参数异常）
    - IndexOutOfBoundsException （下标越界异常）
    - NullPointerException （空指针异常）
    - SecurityException （安全异常）

六、
    自定义异常
    1.创建自定义异常类。
    2.在方法中通过throw关键字抛出异常对象。
    3.如果在当前抛出异常的方法中处理异常，可以使用try-catch语句捕获并处理；否则在方法的声明处通过throws关键字指明要抛出给方法调用者的异常，继续进行下一步操作。
    4.在出现异常方法的调用者中捕获并处理异常。
详见 TestMyException类

 */
public class exceptionWork {
    // throws
    public static void ThrowsException() throws IllegalAccessException{
        throw new IllegalAccessException("demo");
    }
/*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public static void main(String[] args) {
        // try-catch 捕获异常
        // 需要主要一点多个异常捕获时级别相对高的异常应放后面否则底层异常不能被捕获 比如RuntimeException 为Exception的子类
        try {
            //code that might generate exceptions
        } catch (RuntimeException e) {
            //the code of handling exception1
        } catch (Exception e) {
            //the code of handling exception1
        }

        // 算术异常,因为除数不能为零
        int a = 1;
        int b = 0;
        try { // try监控区域
            //throw new ArithmeticException(); // 通过throw语句抛出异常
            System.out.println("a/b的值是：" + a / b);
            System.out.println("this will not be printed!");
        } catch (ArithmeticException e) { // catch捕捉异常
            System.out.println("程序出现异常，变量b不能为0！");
            e.printStackTrace();
        }
        System.out.println("程序正常结束。");

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
        //嵌套异常进入多层嵌套中如果其捕获异常不符合则逐层向外直至捕获异常    也可以详见同包中testThrow类
        try { // try监控区域
            try { // try监控区域
                // 也可以直接通过throw抛出异常
                if (b == 0) throw new ArithmeticException();
                System.out.println("a/b的值是：" + a / b);
                System.out.println("this will not be printed!");
            } catch (NullPointerException e) { // catch捕捉异常,此处为空指针异常无法捕获上面的算数异常所以跳出在下一个catch中捕获
                e.printStackTrace();
            }
        } catch (ArithmeticException e) { // catch捕捉异常
            System.out.println("程序出现异常，变量b不能为0！");
            e.printStackTrace();
        }
/*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
        // 定义方法时直接抛出异常,调用时需要捕获
        try{
            ThrowsException();
        }catch (IllegalAccessException e){
        }

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    }
}

