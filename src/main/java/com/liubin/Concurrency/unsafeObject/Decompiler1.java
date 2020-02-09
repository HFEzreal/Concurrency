package com.liubin.Concurrency.unsafeObject;

import java.io.IOException;

/**
 * javac Decompiler1.java
 * javap -p -v Decompiler1
 * java Decompiler1
 */
public class Decompiler1 {

    public static void main(String[] args) throws IOException {
        String str = "";
        for (int i = 0; i < 10; i++) {
            str += "1";
        }
        System.out.println("String length:" + str.length());
    }
}
