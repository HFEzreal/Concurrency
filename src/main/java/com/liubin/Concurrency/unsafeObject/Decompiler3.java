package com.liubin.Concurrency.unsafeObject;

import lombok.extern.slf4j.Slf4j;

/**
 * javac Decompiler3.java
 * javap -p -v Decompiler3
 * java Decompiler3
 */
@Slf4j
public class Decompiler3 {

    public static void main(String[] args) {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            str.append("1");
        }
        log.info("String length:" + str.length());
    }
}
