package com.example.learningdemo.Thread_12;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Predicate;

/** Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
    at java.util.concurrent.LinkedBlockingQueue.offer(LinkedBlockingQueue.java:416)
    at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1371)
    at com.hollis.ExecutorsDemo.main(ExecutorsDemo.java:16)
    * 如果不设置容量会报这个错误
 * 原因:LinkedBlockingQueue是一个用链表实现的有界阻塞队列，容量可以选择进行设置，不设置的话，将是一个无边界的阻塞队列，最大长度为Integer.MAX_VALUE。
 * 而newFixedThreadPool中创建LinkedBlockingQueue时，并未指定容量。此时，LinkedBlockingQueue就是一个无边界队列，对于一个无边界队列来说，
 * 是可以不断的向队列中加入任务的，这种情况下就有可能因为任务过多而导致内存溢出问题。
 * 上面提到的问题主要体现在newFixedThreadPool和newSingleThreadExecutor两个工厂方法上，并不是说newCachedThreadPool和newScheduledThreadPool这两个方法就安全了，
 * 这两种方式创建的最大线程数可能是Integer.MAX_VALUE，而创建这么多线程，必然就有可能导致OOM。
* */
public class ExecutorsDemo {
    // 使用guava提供的ThreadFactoryBuilder来创建线程池。不仅可以避免OOM的问题，还可以自定义线程名称，更加方便的出错的时候溯源。
    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();
    private static ExecutorService pool = new ThreadPoolExecutor(5, 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());


    // 此方法创建线程没做限制的话会出现内存溢出错误
    /**
     * Executor 管理多个异步任务的执行，而无需程序员显式地管理线程的生命周期。这里的异步是指多个任务的执行互不干扰，不需要进行同步操作。
     * 主要有三种 Executor：
     * CachedThreadPool：一个任务创建一个线程；
     * FixedThreadPool：所有任务只能使用固定大小的线程；
     * SingleThreadExecutor：相当于大小为 1 的 FixedThreadPool。
     */
    private static ExecutorService executor = Executors.newFixedThreadPool(15);
    private static ExecutorService executorByNewCachedThreadPool = Executors.newCachedThreadPool();
    private static ExecutorService executorByNewSingleThreadExecutor = Executors.newSingleThreadExecutor();


//    public static void main(String[] args) {
//        // 此处ExecutorService.execute()方法调用的为自定义的线程
//        executorByNewCachedThreadPool.execute(new MyRunnable());
//        executorByNewSingleThreadExecutor.execute(new MyThread());
////        executorByNewSingleThreadExecutor.execute(new SubThread());
//
//
//
//
//        for (int i = 0; i < Integer.MAX_VALUE; i++) {
//            executor.execute(new SubThread());
//            System.out.println(i);
//        }
//
//
//z
//        for (int i = 0; i < Integer.MAX_VALUE; i++) {
//            pool.execute(new SubThread());
//        }
//    }
//}

    static <T> Predicate<T> distinctByKey1(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("I am running");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("the main thread ready to exit");


    }

    class SubThread implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                //do nothing
            }
        }
    }
}

