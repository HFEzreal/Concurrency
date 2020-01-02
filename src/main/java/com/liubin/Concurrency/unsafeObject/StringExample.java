package com.liubin.Concurrency.unsafeObject;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @description String对象
 * @author liubin
 * @date 19/12/31 09:58 
 */
@Slf4j
public class StringExample {

    //请求总数
    private final static int clientTotal = 10000;
    //并发数
    private final static int threadTotal = 500;

    //线程不安全
    private static String str = "";
    //线程不安全
    private static StringBuilder strBuilder = new StringBuilder();
    //线程安全
    private static StringBuffer strBuffer = new StringBuffer();

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    append();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("count error", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("String length:{}", str.length());
        log.info("String length:{}", strBuilder.length());
        log.info("String length:{}", strBuffer.length());
    }

    private static void append() {
        str += "1";
        strBuilder.append("1");
        strBuffer.append("1");
    }
}
