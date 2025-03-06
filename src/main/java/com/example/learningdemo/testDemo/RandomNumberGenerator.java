package com.example.learningdemo.testDemo;


import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RandomNumberGenerator {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Random random = new Random();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                int number = random.nextInt(10) + 1; // 生成1到10之间的随机数
                System.out.println("Generated number: " + number);

                if (number == 10) {
                    System.out.println("Generated 10, stopping the scheduler.");
                    scheduler.shutdown();
                }
            }
        };

        // 每隔1秒执行一次任务
        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
    }
}