package com.example.learningdemo.Thread_12;


import java.util.concurrent.*;

/**
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
    以下使用 Lambda 创建线程，相当于创建了一个匿名内部线程。
五、互斥同步
    互斥同步属于一种悲观的并发策略，总是认为只要不去做正确的同步措施，那就肯定会出现问题。无论共享数据是否真的会出现竞争，
    它都要进行加锁（这里讨论的是概念模型，实际上虚拟机会优化掉很大一部分不必要的加锁）、用户态核心态转换、维护锁计数器和检查是否有被阻塞的线程需要唤醒等操作。
    同步锁synchronized
六、线程之间的协作
    1.join()
    在线程中调用另一个线程的 join() 方法，会将当前线程挂起，而不是忙等待，直到目标线程结束。
    2.wait() notify() notifyAll()
    调用 wait() 使得线程等待某个条件满足，线程在等待时会被挂起，当其他线程的运行使得这个条件满足时，其它线程会调用 notify() 或者 notifyAll() 来唤醒挂起的线程。
    它们都属于 Object 的一部分，而不属于 Thread。
    只能用在同步方法或者同步控制块中使用，否则会在运行时抛出 IllegalMonitorStateException。
    使用 wait() 挂起期间，线程会释放锁。这是因为，如果没有释放锁，那么其它线程就无法进入对象的同步方法或者同步控制块中，那么就无法执行 notify() 或者 notifyAll() 来唤醒挂起的线程，造成死锁。
    3.wait() 和 sleep() 的区别
    wait() 是 Object 的方法，而 sleep() 是 Thread 的静态方法；
    wait() 会释放锁，sleep() 不会。
七、线程状态
    1.新建（NEW）
    创建后尚未启动。
    2.可运行（RUNABLE）
    正在 Java 虚拟机中运行。但是在操作系统层面，它可能处于运行状态，也可能等待资源调度（例如处理器资源），资源调度完成就进入运行状态。所以该状态的可运行是指可以被运行，具体有没有运行要看底层操作系统的资源调度。
    3.阻塞（BLOCKED）
    请求获取 monitor lock 从而进入 synchronized 函数或者代码块，但是其它线程已经占用了该 monitor lock，所以出于阻塞状态。要结束该状态进入从而 RUNABLE 需要其他线程释放 monitor lock。
    4.无限期等待（WAITING）
    等待其它线程显式地唤醒。
    阻塞和等待的区别在于，阻塞是被动的，它是在等待获取 monitor lock。而等待是主动的，通过调用 Object.wait() 等方法进入。

                进入方法	                                          退出方法
    没有设置 Timeout 参数的 Object.wait() 方法	        Object.notify() / Object.notifyAll()
    没有设置 Timeout 参数的 Thread.join() 方法	        被调用的线程执行完毕
    LockSupport.park() 方法	                        LockSupport.unpark(Thread)

    5.限期等待（TIMED_WAITING）
    无需等待其它线程显式地唤醒，在一定时间之后会被系统自动唤醒。

            进入方法	                                                退出方法
    Thread.sleep() 方法	                                            时间结束
    设置了 Timeout 参数的 Object.wait() 方法	        时间结束 / Object.notify() / Object.notifyAll()
    设置了 Timeout 参数的 Thread.join() 方法	                时间结束 / 被调用的线程执行完毕
    LockSupport.parkNanos() 方法	                            LockSupport.unpark(Thread)
    LockSupport.parkUntil() 方法	                            LockSupport.unpark(Thread)

    6.死亡（TERMINATED）
    可以是线程结束任务之后自己结束，或者产生了异常而结束。
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

        System.out.println("测试join()方法-------------------------------------------------------------------------");
        //对于以下代码，虽然 b 线程先启动，但是因为在 b 线程中调用了 a 线程的 join() 方法，b 线程会等待 a 线程结束才继续执行，
        // 因此最后能够保证 a 线程的输出先于 b 线程的输出。
        A a = new A();
        B b = new B(a);
        b.start();
        a.start();

        System.out.println("测试wait() notify() notifyAll()方法-------------------------------------------------------------------------");
        ExecutorService testWaitNotify = Executors.newCachedThreadPool();
        ThreadTest threadTest = new ThreadTest();
        // after中线程进入wait状态,下一行调用before()唤醒线程after()继续执行
        testWaitNotify.execute(() -> threadTest.after());
        testWaitNotify.execute(() -> threadTest.before());






        System.out.println("线程不安全示例-------------------------------------------------------------------------");
        // 如果多个线程对同一个共享数据进行访问而不采取同步操作的话，那么操作的结果是不一致的。
        final int threadSize = 1000;
        ThreadUnsafeExample example = new ThreadUnsafeExample();
        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService executorServiceUnSafe = Executors.newCachedThreadPool();
        for (int i = 0; i < threadSize; i++) {
            executorServiceUnSafe.execute(() -> {
                // add()方法添加同步锁synchronized可保证累加为1000
                example.add();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorServiceUnSafe.shutdown();
        System.out.println(example.get());




        System.out.println("局部变量线程安全-------------------------------------------------------------------------");
        StackClosedExample exampleByVariable = new StackClosedExample();
        ExecutorService executorByVariable = Executors.newCachedThreadPool();
        executorByVariable.execute(() -> exampleByVariable.add100());
        executorByVariable.execute(() -> exampleByVariable.add100());
        executorService.shutdown();

    }


    // 测试synchronized同步锁(同步代码块)
    public void synchronizedTest() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }
    // 同步方法
    public synchronized void CommonTest() {
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
        }
    }


    // 测试wait() notify() notifyAll()
    public synchronized void before() {
        System.out.println("before");
        notifyAll();
    }

    public synchronized void after() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after");
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


// 此处测试使用线程join()方法
class A extends Thread {
    @Override
    public void run() {
        System.out.println("A run");
    }
}

class B extends Thread {

    private A a;

    B(A a) {
        this.a = a;
    }

    @Override
    public void run() {
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("B run");
    }
}

// 测试线程不安全示例
 class ThreadUnsafeExample {

    private int cnt = 0;

    //public synchronized void add() {
    public synchronized void add() {
        cnt++;
    }

    public int get() {
        return cnt;
    }
}

//  多个线程访问同一个方法的局部变量时，不会出现线程安全问题，因为局部变量存储在虚拟机栈中，属于线程私有的。
 class StackClosedExample {
    public void add100() {
        int cnt = 0;
        Integer integer=0;
        for (int i = 0; i < 100; i++) {
            cnt++;
            integer++;
        }
        System.out.println(cnt);
        System.out.println("局部变量为引用类型时"+integer);
    }
}