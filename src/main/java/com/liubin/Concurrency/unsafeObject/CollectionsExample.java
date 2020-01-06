package com.liubin.Concurrency.unsafeObject;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.*;

/**
 * @description 集合
 * @author liubin
 * @date 19/12/31 09:58 
 */
@Slf4j
public class CollectionsExample {

    //请求总数
    private final static int clientTotal = 10000;
    //并发数
    private final static int threadTotal = 200;
    //线程不安全
    private static List<Integer> list = new ArrayList<>();
    private static List<Integer> safelist = new CopyOnWriteArrayList<>();
    private static Vector<Integer> vector = new Vector<>();
    private static Set<Integer> set = new HashSet<>();
    private static Set<Integer> safeset = new CopyOnWriteArraySet<>();
    private static Map<Integer, Integer> map = new HashMap<>();
    private static Map<Integer, Integer> table = new Hashtable<>();
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
        log.info("list size:{}", list.size());
        log.info("safelist size:{}", safelist.size());
        log.info("vector size:{}", vector.size());
        log.info("set size:{}", set.size());
        log.info("safeset size:{}", safeset.size());
        log.info("map size:{}", map.size());
        log.info("map size:{}", table.size());
        log.info("map size:{}", concurrentHashMap.size());
    }


    //线程不安全
    private static void add(int i) {
        list.add(i);
        set.add(i);
        map.put(i, i);
        safelist.add(i);
        vector.add(i);
        safeset.add(i);
        table.put(i, i);
        concurrentHashMap.put(i, i);
    }
}
