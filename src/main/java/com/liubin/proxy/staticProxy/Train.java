package com.liubin.proxy.staticProxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liubin
 * @description 坐火车
 * @date 2020/2/8 12:07
 */
@Slf4j
public class Train implements Travel {

    @Override
    public void travel() {
        log.info("坐火车旅行");
    }

    @Override
    public String hike() {
        return null;
    }
}