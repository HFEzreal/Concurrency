package com.liubin.Concurrency.syncContainer;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @description ConcurrentHashMap
 * @author liubin
 * @date 20/1/6 09:39 
 */
@Slf4j
public class ConcurrentHashMapExample {

    //请求总数
    private final static int clientTotal = 5000;
    //并发数
    private final static int threadTotal = 200;
    //线程安全
    private static Map<Integer, Integer> map = new ConcurrentHashMap<>();
    //线程不安全
    private static Map<Integer, Integer> map2 = new HashMap<>();

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
        log.info("map size:{}", map.size());
        log.info("map2 size:{}", map2.size());
        for(Integer index:map.keySet()){
            System.out.println(index);
        }
    }

    private static void add(int i) {
        int r = (int) (Math.random() * 5000);
        map.put(r + i, i);
        map2.put(r + i, i);
    }
}
