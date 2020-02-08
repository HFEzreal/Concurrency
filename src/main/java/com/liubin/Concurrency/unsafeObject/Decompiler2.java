package com.liubin.Concurrency.unsafeObject;

import lombok.extern.slf4j.Slf4j;

/**
 * javac Decompiler2.java
 * javap -p -v Decompiler2
 * java Decompiler2
 */
@Slf4j
public class Decompiler2 {

    public static void main(String[] args) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            str.append("1");
        }
        log.info("String length:" + str.length());
    }
}
