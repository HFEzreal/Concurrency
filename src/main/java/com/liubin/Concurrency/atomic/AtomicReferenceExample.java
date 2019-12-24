package com.liubin.Concurrency.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @description 原子类使用
 * @author liubin
 * @date 19/12/23 15:46 
 */
@Slf4j
public class AtomicReferenceExample {
    private static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    private static AtomicReference<String> atomicReference2 = new AtomicReference<>("liubin");

    public static void main(String[] args) {
        if (atomicReference.compareAndSet(100, 120)) {
            log.info("execute success 1 {}", atomicReference.get());
        } else {
            log.info("execute faild 1 {}", atomicReference.get());
        }
        if (atomicReference.compareAndSet(100, 120)) {
            log.info("execute success 2 {}", atomicReference.get());
        } else {
            log.info("execute faild 2 {}", atomicReference.get());
        }

        if (atomicReference2.compareAndSet("liubin", "liubin2")) {
            log.info("String execute success 1 {}", atomicReference.get());
        } else {
            log.info("String execute faild 1 {}", atomicReference.get());
        }
        if (atomicReference2.compareAndSet("liubin2", "liubin3")) {
            log.info("String execute success 2 {}", atomicReference.get());
        } else {
            log.info("String execute faild 2 {}", atomicReference.get());
        }
    }
}
