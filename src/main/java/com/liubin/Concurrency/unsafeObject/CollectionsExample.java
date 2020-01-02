package com.liubin.Concurrency.unsafeObject;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.*;

/**
 * @description time对象
 * @author liubin
 * @date 19/12/31 09:58 
 */
@Slf4j
public class CollectionsExample {

    //请求总数
    private final static int clientTotal = 2000;
    //并发数
    private final static int threadTotal = 200;
    //线程不安全
    private static List<Integer> list = new ArrayList<Integer>();
    private static Vector<Integer> vector = new Vector<Integer>();
    private static Set<Integer> set = new HashSet<>();
    private static Map<Integer, Integer> map = new HashMap<>();
    private static Map<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();


    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add(count);
                    semaphore.release();
                } catch (Exception e) {
                    log.error("count error", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("list size:{}",list.size());
        log.info("vector size:{}",vector.size());
        log.info("set size:{}",set.size());
        log.info("map size:{}",map.size());
        log.info("map size:{}",concurrentHashMap.size());
    }


    //线程不安全
    private static void add(int i) {
        list.add(i);
        vector.add(i);
        set.add(i);
        map.put(i, i);
        concurrentHashMap.put(i, i);
    }
}
