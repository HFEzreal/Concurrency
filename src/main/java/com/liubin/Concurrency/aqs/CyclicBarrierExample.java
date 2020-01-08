package com.liubin.Concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @description 计数器
 * @author liubin
 * @date 20/1/8 17:15 
 */
@Slf4j
public class CyclicBarrierExample {

    private static final int total = 100;
    private static final int client = 5;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(client);
        for (int i = 0; i < total; i++) {
            Thread.sleep(100);
            final int index = i;
            executorService.execute(() -> {
                try {
                    Thread.sleep(100);
                    log.info("{} ready", index);
//                    cyclicBarrier.await();
                    cyclicBarrier.await(200, TimeUnit.MILLISECONDS);
                    log.info("{} continue", index);
                } catch (Exception e) {
                    log.error("error", e);
                }
            });
        }
        executorService.shutdown();
    }

}
