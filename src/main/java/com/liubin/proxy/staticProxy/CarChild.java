package com.liubin.proxy.staticProxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liubin
 * @description 继承代理
 * @date 2020/2/8 12:04
 */
@Slf4j
public class CarChild extends Car {

    @Override
    public void travel() {
        log.info("旅行准备工作");
        super.travel();
        log.info("旅行总结工作");
    }

}