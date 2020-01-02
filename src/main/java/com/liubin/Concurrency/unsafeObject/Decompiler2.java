package com.liubin.Concurrency.unsafeObject;

/**
 * javac Decompiler2.java
 * javap -p -v Decompiler2
 * java Decompiler2
 */
public class Decompiler2 {

    public static void main(String[] args) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            str.append("1");
        }
        System.out.println("String length:" + str.length());
    }
}
