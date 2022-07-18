package com.example.learningdemo.Thread_12;


import java.util.concurrent.*;

/*
    线程
一、有四种使用线程的方法：
    实现 Runnable 接口；
    实现 Callable 接口；
    继承 Thread 类。
    通过线程池创建线程。
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
三、Executors
    Executors 是一个Java中的工具类。提供工厂方法来创建不同类型的线程池。
    newFiexedThreadPool(int Threads)：创建固定数目线程的线程池。
    newCachedThreadPool()：创建一个可缓存的线程池，调用execute 将重用以前构造的线程（如果线程可用）。如果没有可用的线程，则创建一个新线程并添加到池中。终止并从缓存中移除那些已有 60 秒钟未被使用的线程。
    newSingleThreadExecutor()创建一个单线程化的Executor。
    newScheduledThreadPool(int corePoolSize)创建一个支持定时及周期性的任务执行的线程池，多数情况下可用来替代Timer类。
    ---------------------------------但尽量不要用Executors创建线程,推荐ThreadPoolExecutor----------------------------------------------------------------------
    因为使用Executors创建线程池可能会导致OOM(OutOfMemory ,内存溢出)详见ExecutorsDemo

    Daemon
    守护线程是程序运行时在后台提供服务的线程，不属于程序中不可或缺的部分。
    当所有非守护线程结束时，程序也就终止，同时会杀死所有守护线程。
    main() 属于非守护线程。
    在线程启动之前使用 setDaemon() 方法可以将一个线程设置为守护线程。

    sleep()
    Thread.sleep(millisec) 方法会休眠当前正在执行的线程，millisec 单位为毫秒。
    sleep() 可能会抛出 InterruptedException，因为异常不能跨线程传播回 main() 中，因此必须在本地进行处理。线程中抛出的其它异常也同样需要在本地进行处理。

    yield()
    对静态方法 Thread.yield() 的调用声明了当前线程已经完成了生命周期中最重要的部分，可以切换给其它线程来执行。
    该方法只是对线程调度器的一个建议，而且也只是建议具有相同优先级的其它线程可以运行。
四、中断
    InterruptedException
    通过调用一个线程的 interrupt() 来中断该线程，如果该线程处于阻塞、限期等待或者无限期等待状态，那么就会抛出 InterruptedException，从而提前结束该线程。
    但是不能中断 I/O 阻塞和 synchronized 锁阻塞。
    对于以下代码，在 main() 中启动一个线程之后再中断它，由于线程中调用了 Thread.sleep() 方法，
    因此会抛出一个 InterruptedException，从而提前结束线程，不执行之后的语句。
    interrupted()
    如果一个线程的 run() 方法执行一个无限循环，并且没有执行 sleep() 等会抛出 InterruptedException 的操作，那么调用线程的 interrupt() 方法就无法使线程提前结束。
    但是调用 interrupt() 方法会设置线程的中断标记，此时调用 interrupted() 方法会返回 true。
    因此可以在循环体中使用 interrupted() 方法来判断线程是否处于中断状态，从而提前结束线程。
    Executor 的中断操作
    调用 Executor 的 shutdown() 方法会等待线程都执行完毕之后再关闭，但是如果调用的是 shutdownNow() 方法，则相当于调用每个线程的 interrupt() 方法。
    以下使用 Lambda 创建线程，相当于创建了一个匿名内部线程。\
五、互斥同步
    同步锁synchronized

* */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //  使用 Runnable 实例再创建一个 Thread 实例，然后调用 Thread 实例的 start() 方法来启动线程。
        System.out.println("通过实现runnable接口创建线程-------------------------------------------------------");
        MyRunnable instance = new MyRunnable();
        Thread threadRunnable = new Thread(instance);
        threadRunnable.start();

        // 将线程开启守护线程
//        threadRunnable.setDaemon(true);
        // 休眠当前线程300ms
        Thread.sleep(300);
        // 建议切换其他线程执行
        Thread.yield();


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

        //通过线程池创建线程
        //所谓线程池本质是一个hashSet。多余的任务会放在阻塞队列中。
        //线程池的创建方式其实也有很多，也可以通过Executors静态工厂构建，但一般不建议。建议使用线程池来创建线程，
        // 并且建议使用带有ThreadFactory参数的ThreadPoolExecutor（需要依赖guava）构造方法设置线程名字
        System.out.println("通过创建线程池创建线程-------------------------------------------------------");
        //打印当前线程名称
        System.out.println(Thread.currentThread().getName());
        // 创建线程池  推荐此方法创建线程
        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //打印当前线程名称
                System.out.println(Thread.currentThread().getName());
                System.out.println("已通过线程池创建线程");
            }
        });
        //ExecutorService是Executors的代理对象,详见代理模式
        //通过Executors.newCachedThreadPool()创建线程池
        //  不推荐此方法创建线程
        System.out.println("通过通过Executors.newXXXXX()创建线程池创建线程-------------------------------------------------------");
        ExecutorService executorServiceByExecutors = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorServiceByExecutors.execute(new MyRunnable());
        }
        executorServiceByExecutors.shutdown();


        System.out.println("测试InterruptedException()中断线程-------------------------------------------------------");
        //通过调用一个线程的 interrupt() 来中断该线程，如果该线程处于阻塞、限期等待或者无限期等待状态，
        // 那么就会抛出 InterruptedException，从而提前结束该线程。但是不能中断 I/O 阻塞和 synchronized 锁阻塞。
        Thread threadByTestInterrupted = new MyThreadByTestInterrupted();
        threadByTestInterrupted.start();
        threadByTestInterrupted.interrupt();
        System.out.println("threadByTestInterrupted  run----");


        System.out.println("循环体中使用 interrupted() 方法来判断线程是否处于中断状态，从而提前结束线程-------------------------------------------------------");
        // 如果一个线程的 run() 方法执行一个无限循环，并且没有执行 sleep() 等会抛出 InterruptedException 的操作，那么调用线程的 interrupt() 方法就无法使线程提前结束。
        // 但是调用 interrupt() 方法会设置线程的中断标记，此时调用 interrupted() 方法会返回 true。
        // 因此可以在循环体中使用 interrupted() 方法来判断线程是否处于中断状态，从而提前结束线程。
        Thread myThreadInterrupted = new MyThreadInterrupted();
        myThreadInterrupted.start();
        myThreadInterrupted.interrupt();

        System.out.println("Executor 的中断操作-------------------------------------------------------------------------");
        //调用 Executor 的 shutdown() 方法会等待线程都执行完毕之后再关闭，但是如果调用的是 shutdownNow() 方法，则相当于调用每个线程的 interrupt() 方法。
        ExecutorService executorThreadPool = Executors.newCachedThreadPool();
        executorThreadPool.execute(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Thread run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorThreadPool.shutdownNow();
        System.out.println("Main run");


        // 测试synchronized同步锁线程
        //它只作用于同一个对象，如果调用两个对象上的同步代码块，就不会进行同步。
        //对于以下代码，使用 ExecutorService 执行了两个线程，由于调用的是同一个对象的同步代码块，因此这两个线程会进行同步，当一个线程进入同步语句块时，另一个线程就必须等待。
        System.out.println("测试synchronized同步锁线程--同一个线程调用两次代码块-----------------------------------------------------------------------");
/*        ThreadTest ThreadBySynchronized = new ThreadTest();
        ExecutorService ExecutorBySynchronized = Executors.newCachedThreadPool();
        ExecutorBySynchronized.execute(() -> ThreadBySynchronized.synchronizedTest());
        ExecutorBySynchronized.execute(() -> ThreadBySynchronized.synchronizedTest());*/

        // 两个线程调用了不同对象的同步代码块，因此这两个线程就不需要同步。从输出结果可以看出，两个线程交叉执行。
        System.out.println("测试synchronized同步锁线程--两个线程调用同一个代码块-------------------------------------------------------------------------");
        ThreadTest ThreadBySynchronized1 = new ThreadTest();
        ThreadTest ThreadBySynchronized2 = new ThreadTest();
        ExecutorService ExecutorBySynchronized1 = Executors.newCachedThreadPool();
        ExecutorBySynchronized1.execute(() -> ThreadBySynchronized1.synchronizedTest());
        ExecutorBySynchronized1.execute(() -> ThreadBySynchronized2.synchronizedTest());
    }


    // 测试synchronized同步锁
    public void synchronizedTest() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }

    public synchronized void CommonTest() {
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
        }
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

// 测试InterruptedException()中断线程
class MyThreadByTestInterrupted extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println("Thread run");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// interrupted() 判断当前线程是否处于中断状态,从而提前结束进程
class MyThreadInterrupted extends Thread {
    @Override
    public void run() {
        while (!interrupted()) {
            // ..
        }
        System.out.println("MyThreadInterrupted 处于中断状态,结束进程");
    }
}


