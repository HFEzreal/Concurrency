package com.liubin.Concurrency.unsafeObject;


/**
 * javac Decompiler3.java
 * javap -p -v Decompiler3
 * java Decompiler3
 */
public class Decompiler3 {

    public static void main(String[] args) {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            str.append("1");
        }
        System.out.println("String length:" + str.length());
    }
}
