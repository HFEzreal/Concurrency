package com.liubin.Concurrency.threadLocal;

import lombok.extern.slf4j.Slf4j;

/**
 * @description ThreadLocal测试
 * @author liubin
 * @date 19/12/27 09:29 
 */
@Slf4j
public class ThreadLocalExample {

    private final static ThreadLocal<Long> threadLocal = ThreadLocal.withInitial(() -> 0L);

    public static void add(Long i) {
        threadLocal.set(i);
    }

    public static long getNext() {
        threadLocal.set(threadLocal.get() + 1);
        return threadLocal.get();
    }

    public static Long get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }

    public static class MyRun extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                log.info("{}:{}", Thread.currentThread().getName(), getNext());
            }
        }
    }

    public static void main(String[] args) {
        MyRun myRun1 = new MyRun();
        MyRun myRun2 = new MyRun();
        MyRun myRun3 = new MyRun();
        myRun1.start();
        myRun2.start();
        myRun3.start();
    }

}
