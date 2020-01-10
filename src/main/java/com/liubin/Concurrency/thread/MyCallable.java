package com.liubin.Concurrency.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @description 声明线程
 * @author liubin
 * @date 20/1/9 17:40 
 */
public class MyCallable implements Callable<String> {
    @Override
    public String call() {
        return "ok";
    }

    public static void main(String[] args) throws Exception {
        MyCallable call = new MyCallable();
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(call);
        future.get();
    }
}
