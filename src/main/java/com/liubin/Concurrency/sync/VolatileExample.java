package com.liubin.Concurrency.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @description 模拟并发
 * @author liubin
 * @date 19/12/23 14:36 
 */
@Slf4j
public class VolatileExample {
    //请求总数
    private final static int clientTotal = 5000;
    //并发数
    private final static int threadTotal = 200;

    private static volatile int count = 0;
    private static int count2 = 0;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int index = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add(index);
                    semaphore.release();
                } catch (Exception e) {
                    log.error("count error", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count);
        log.info("count:{}", count2);
    }

    private static void add(int i) {
        count++;
        count2++;
    }

}
