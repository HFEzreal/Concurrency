package com.liubin.Concurrency.unsafeObject;

/**
 * javac Decompiler1.java
 * javap -p -v Decompiler1
 * java Decompiler1
 */
public class Decompiler1 {

    public static void main(String[] args) {
        String str = "";
        for (int i = 0; i < 10; i++) {
            str += "1";
        }
        System.out.println("String length:" + str.length());
    }
}
