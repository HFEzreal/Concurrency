package com.liubin.proxy.staticProxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liubin
 * @description 代理类
 * @date 2020/2/8 12:09
 */
@Slf4j
public class TravelProxy implements Travel {

    private Travel travel;

    public TravelProxy(Travel travel) {
        this.travel = travel;
    }

    @Override
    public void travel() {

        log.info("旅行准备工作");
        travel.travel();
        log.info("旅行总结工作");
    }

}