package com.liubin.Concurrency.syncContainer;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.*;

/**
 * @description 线程安全且有序的容器
 * @author liubin
 * @date 20/1/6 09:21 
 */
@Slf4j
public class ConcurrentSkipExample {
    //请求总数
    private final static int clientTotal = 5000;
    //并发数
    private final static int threadTotal = 200;
    //有序，线程安全
    private static Map<Integer, Integer> map = new ConcurrentSkipListMap<>();
    //无序，线程不安全
    private static Map<Integer, Integer> map2 = new HashMap<>();
    //有序，线程安全
    private static Set<Integer> set = new ConcurrentSkipListSet<>();
    //无序，线程不安全
    private static Set<Integer> set2 = new HashSet<>();
    //无序，线程安全
    private static Set<Integer> set3 = new CopyOnWriteArraySet<>();

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
        log.info("set size:{}", set.size());
        log.info("set2 size:{}", set2.size());
        log.info("set3 size:{}", set3.size());
    }

    private static void add(int i) {
        double d = Math.random();
        int k = (int) (d * 5000);
        map.put(i, i);
        map2.put(i, i);
        set.add(i);
        set2.add(i);
        set3.add(i);
    }
}
