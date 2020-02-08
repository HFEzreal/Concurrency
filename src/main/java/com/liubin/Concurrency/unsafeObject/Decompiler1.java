package com.liubin.Concurrency.unsafeObject;

import lombok.extern.slf4j.Slf4j;

/**
 * javac Decompiler1.java
 * javap -p -v Decompiler1
 * java Decompiler1
 */
@Slf4j
public class Decompiler1 {

    public static void main(String[] args) {
        String str = "";
        for (int i = 0; i < 10; i++) {
            str += "1";
        }
        log.info("String length:" + str.length());
    }
}
