package com.liubin.Concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @description 信号量
 * @author liubin
 * @date 20/1/8 16:20 
 */
@Slf4j
public class SeampHoreExample {

    private static final int total = 100;
    private static final int client = 10;

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(client);
        for (int i = 0; i < total; i++) {
            final int index = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();//获取一个许可
                    semaphore.acquire(10);//一次获取10个许可
                    semaphore.tryAcquire();//尝试获取一个许可，成功返回true，失败返回false
                    semaphore.tryAcquire(1,TimeUnit.SECONDS);//尝试获取一个许可，如果失败继续尝试获取，直到超时时间1秒，成功返回true，失败返回false
                    Thread.sleep(100);
                    log.info("{}", index);
                    semaphore.release();//释放许可
                } catch (Exception e) {
                    log.error("error", e);
                }
            });
        }
        executorService.shutdown();
    }


}
