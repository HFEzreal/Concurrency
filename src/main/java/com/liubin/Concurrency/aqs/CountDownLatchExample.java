package com.liubin.Concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description 同步计数器
 * @author liubin
 * @date 20/1/7 11:30 
 */
@Slf4j
public class CountDownLatchExample {

    private static final int totalCount = 10000;

    public static void main(String[] args) throws Exception {
        test2();
    }

    private static void test() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(totalCount);
        for (int i = 0; i < totalCount; i++) {
            final int index = i;
            executorService.execute(() -> {
                log.info("my run excute {}", index);
                countDownLatch.countDown();
            });
        }
        //计数器归零时：唤醒下一步操作
        countDownLatch.await();
        executorService.shutdown();
        log.info(" excute finish ...");
    }

    private static void test2() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(totalCount);
        for (int i = 0; i < totalCount; i++) {
            final int index = i;
            executorService.execute(() -> {
                try {
                    Thread.sleep(9);
                    log.info("my run excute {}", index);
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    log.error("error", e);
                }
            });
        }
        //计数器归零或者超时时：唤醒下一步操作
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info(" countDownLatch wait time out ...");
        executorService.shutdown();
        log.info(" excute finish ...");
    }

}
