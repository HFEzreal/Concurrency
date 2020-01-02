package com.liubin.Concurrency.unsafeObject;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @description time对象
 * @author liubin
 * @date 19/12/31 09:58 
 */
@Slf4j
public class DateTimeExample {

    //请求总数
    private final static int clientTotal = 2000;
    //并发数
    private final static int threadTotal = 200;
    //线程不安全
    private static SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyyMMdd");
    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMdd");


    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    dateTime();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("count error", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }

    //线程不安全
    private static void simpleDateFormat() {
        try {
            simpleFormat.parse("20181112");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //线程安全
    private static void dateTime() {
        formatter.parseDateTime("20181112");
    }
}
