package com.liubin.Concurrency.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @description 继承thread实现
 * @author liubin
 * @date 20/1/9 18:31 
 */
@Slf4j
public class MyThread extends Thread {

    @Override
    public void run() {
        log.info("继承 thread ");
    }
}
