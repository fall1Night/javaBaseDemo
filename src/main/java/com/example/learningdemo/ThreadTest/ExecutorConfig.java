package com.example.learningdemo.ThreadTest;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池配置
 *
 * @version V1.0
 * @since 2021-06-08 15:39
 */
public class ExecutorConfig {
    private static int maxPoolSize = Runtime.getRuntime().availableProcessors();
    private volatile static ExecutorService executorService;

    private ExecutorConfig() {
    }

    public static ExecutorService getThreadPool() {
        if (executorService == null) {
            synchronized (ExecutorConfig.class) {
                if (executorService == null) {
                    executorService = newThreadPool();
                }
            }
        }
        return executorService;
    }

    private static ExecutorService newThreadPool() {
        int queueSize = 500;
        int corePool = Math.min(5, maxPoolSize);
        return new ThreadPoolExecutor(corePool, maxPoolSize, 10000L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(queueSize), new ThreadPoolExecutor.AbortPolicy());
    }
}