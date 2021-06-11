package com.liubin.Concurrency.singleton;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @description 枚举模式
 * @author liubin
 * @date 19/12/24 17:55
 */
@Slf4j
public class EnumMode implements Serializable {

    private static final long serialVersionUID = -6718249352186206576L;

    private static EnumMode instance;

    //推荐使用这种方式
    public enum Singleton {
        INSTANCE;
        Singleton() {
            instance = new EnumMode();
        }

        public EnumMode getInstance() {
            return instance;
        }
    }

    public static void main(String[] args) {
        log.info(Singleton.INSTANCE.getInstance().toString());
    }

}
