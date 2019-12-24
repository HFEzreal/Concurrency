package com.liubin.Concurrency.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @description 模拟并发
 * @author liubin
 * @date 19/12/23 14:36 
 */
@Slf4j
public class LongAdderExample {
    //请求总数
    private final static int clientTotal = 10000;
    //并发数
    private final static int threadTotal = 500;
    //在高并发场景下：性能优于AtomicLong
    private static LongAdder count = new LongAdder();//默认是0


    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("count error", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count.longValue());
    }

    private static void add() {
        count.increment();
    }

}
