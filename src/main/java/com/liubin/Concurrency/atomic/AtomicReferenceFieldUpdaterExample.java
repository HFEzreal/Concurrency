package com.liubin.Concurrency.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @description 原子类使用
 * @author liubin
 * @date 19/12/23 15:46 
 */
@Slf4j
public class AtomicReferenceFieldUpdaterExample {

    //更新某个类的某个字段
    private static AtomicReferenceFieldUpdater<AtomicReferenceFieldUpdaterExample, Integer> updater = AtomicReferenceFieldUpdater.newUpdater(AtomicReferenceFieldUpdaterExample.class, Integer.class, "count");
    @Getter
    private volatile Integer count = 10;


    public static void main(String[] args) {
        AtomicReferenceFieldUpdaterExample example = new AtomicReferenceFieldUpdaterExample();
        if(updater.compareAndSet(example,10,12)){
            log.info("execute success {}",example.getCount());
        }else{
            log.info("execute failed {}",example.getCount());
        }

    }
}
