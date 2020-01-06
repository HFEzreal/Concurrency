package com.liubin.Concurrency.syncContainer;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @description 线程安全的容器
 * @author liubin
 * @date 20/1/6 09:16 
 */
@Slf4j
public class CopyOnWriteExample {

    //请求总数
    private final static int clientTotal = 10000;
    //并发数
    private final static int threadTotal = 500;

    //线程安全
    private static List<Integer> list = new CopyOnWriteArrayList<>();
    //线程不安全
    private static List<Integer> list2 = new ArrayList<>();
    //线程安全
    private static Set<Integer> set = new CopyOnWriteArraySet<>();
    //线程不安全
    private static Set<Integer> set2 = new HashSet<>();

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
        log.info("list size:{}", list.size());
        log.info("list2 size:{}", list2.size());
        log.info("set size:{}", set.size());
        log.info("set2 size:{}", set2.size());
    }

    private static void add(int i) {
        list.add(i);
        list2.add(i);
        set.add(i);
        set2.add(i);
    }
}
