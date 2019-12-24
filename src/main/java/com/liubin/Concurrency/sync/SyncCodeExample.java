package com.liubin.Concurrency.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @description synchronized使用示例
 * @author liubin
 * @date 19/12/23 14:36 
 */
@Slf4j
public class SyncCodeExample {
    //请求总数
    private final static int clientTotal = 5000;
    //并发数
    private final static int threadTotal = 200;

    private static int countCodeBlock = 0;
    private static int countNormalMethod = 0;
    private static int countStaticMethod = 0;
    private static int countClass = 0;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            SyncCodeExample syncExample = new SyncCodeExample();
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    syncExample.codeBlockSync();
                    syncExample.normalMethodSync();
                    syncExample.staticMethodSync();
                    syncExample.classSync();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("count error", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("countCodeBlock:{}", countCodeBlock);
        log.info("countNormalMethod:{}", countNormalMethod);
        log.info("countStaticMethod:{}", countStaticMethod);
        log.info("countClass:{}", countClass);
    }


    private synchronized void codeBlockSync() {
        //修饰代码块：对象锁
        synchronized (this) {
            countCodeBlock++;
        }
    }

    //修饰方法：对象锁
    private synchronized void normalMethodSync() {
        countNormalMethod++;
    }

    //修饰静态方法：类锁
    private static synchronized void staticMethodSync() {
        countStaticMethod++;
    }

    private void classSync() {
        //修饰类：类锁
        synchronized (SyncCodeExample.class) {
            countClass++;
        }
    }

}
