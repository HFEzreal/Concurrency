package com.liubin.Concurrency.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description 创建线程
 * @author liubin
 * @date 19/12/20 15:48 
 */
public class ThreadPoolExample {
    //最大容量线程池
    private static void newCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new MyRunnable("newCachedThreadPool-task-" + i));
        }
        executorService.shutdown();
    }

    //固定大小的线程池
    private static void newFixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new MyRunnable("newFixedThreadPool-task-" + i));
        }
        executorService.shutdown();
    }

    //单线程池
    private static void newSingleThreadExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new MyRunnable("newSingleThreadExecutor-task-" + i));
        }
        executorService.shutdown();
    }

    //新建一个调度线程池
    private static void newScheduledThreadPool() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        //周期执行任务
        executorService.scheduleAtFixedRate(new MyRunnable("newSingleThreadExecutor-task"), 0, 1, TimeUnit.SECONDS);
        //延迟执行任务
        executorService.scheduleWithFixedDelay(new MyRunnable("newSingleThreadExecutor-task"), 0, 1, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
//        newCachedThreadPool();
        newFixedThreadPool();
//        newSingleThreadExecutor();
//        newScheduledThreadPool();
    }

}
