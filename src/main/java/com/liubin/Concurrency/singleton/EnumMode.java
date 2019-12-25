package com.liubin.Concurrency.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @description 枚举模式
 * @author liubin
 * @date 19/12/24 17:55 
 */
@Slf4j
public class EnumMode {

    //推荐使用这种方式
    public enum Singleton {
        INSTANCE;
        private EnumMode instance;

        Singleton() {
            instance = new EnumMode();
        }

        private EnumMode getInstance() {
            return instance;
        }
    }

    public static void main(String[] args) {
        log.info(Singleton.INSTANCE.getInstance().toString());
    }

}
