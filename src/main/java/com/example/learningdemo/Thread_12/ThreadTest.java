package com.example.learningdemo.Thread_12;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
    线程
一、有三种使用线程的方法：
    实现 Runnable 接口；
    实现 Callable 接口；
    继承 Thread 类。
    实现 Runnable 和 Callable 接口的类只能当做一个可以在线程中运行的任务，不是真正意义上的线程，因此最后还需要通过 Thread 来调用。可以理解为任务是通过线程驱动从而执行的。
    实现接口与继承创建线程的区别:实现接口会更好一些，因为：Java 不支持多重继承，因此继承了 Thread 类就无法继承其它类，但是可以实现多个接口；类可能只要求可执行就行，继承整个 Thread 类开销过大。
二、线程的状态
    线程是有状态的，并且这些状态之间也是可以互相流转的。Java中线程的状态分为6种：
    1.初始(NEW)：新创建了一个线程对象，但还没有调用start()方法。
    2.运行(RUNNABLE)：Java线程中将就绪（READY）和运行中（RUNNING）两种状态笼统的称为“运行”。
      就绪（READY）:线程对象创建后，其他线程(比如main线程）调用了该对象的start()方法。该状态的线程位于可运行线程池中，等待被线程调度选中并分配cpu使用权 。
      运行中（RUNNING）：就绪(READY)的线程获得了cpu 时间片，开始执行程序代码。
    3.阻塞(BLOCKED)：表示线程阻塞于锁（关于锁，在后面章节会介绍）。
    4.等待(WAITING)：进入该状态的线程需要等待其他线程做出一些特定动作（通知或中断）。
    5.超时等待(TIMED_WAITING)：该状态不同于WAITING，它可以在指定的时间后自行返回。
    6. 终止(TERMINATED)：表示该线程已经执行完毕。
* */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException,ExecutionException {
        //  使用 Runnable 实例再创建一个 Thread 实例，然后调用 Thread 实例的 start() 方法来启动线程。
        System.out.println("通过实现runnable接口创建线程-------------------------------------------------------");
        MyRunnable instance = new MyRunnable();
        Thread threadRunnable = new Thread(instance);
        threadRunnable.start();

        //使用 Callable 实例创建一个 Thread 实例,与runnable区别为可以又返回值,返回值通过 FutureTask 进行封装。
        System.out.println("通过实现Callable接口创建线程-------------------------------------------------------");
        MyCallable mc = new MyCallable();
        FutureTask<Integer> ft = new FutureTask<>(mc);
        Thread threadCallable = new Thread(ft);
        threadCallable.start();
        // 获取时需要抛出异常
        System.out.println(ft.get());

        //同样也是需要实现 run() 方法，因为 Thread 类也实现了 Runable 接口。
        //当调用 start() 方法启动一个线程时，虚拟机会将该线程放入就绪队列中等待被调度，当一个线程被调度时会执行该线程的 run() 方法。
        System.out.println("通过继承Thread创建线程-------------------------------------------------------");
        MyThread mt = new MyThread();
        mt.start();
    }

}
//实现 Runnable 接口,需要实现接口中的 run() 方法。
 class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("MyRunnable跑起来了");
    }
}
//实现 Callable 接口,与 Runnable 相比，Callable 可以有返回值，返回值通过 FutureTask 进行封装。
 class MyCallable implements Callable<Integer> {
    public Integer call() {
        return 123;
    }
}
//继承 Thread 类
 class MyThread extends Thread {
    public void run() {
        System.out.println("继承Thread跑起来了");
    }
}