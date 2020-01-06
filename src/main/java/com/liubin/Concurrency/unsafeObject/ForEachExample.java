package com.liubin.Concurrency.unsafeObject;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description for循环
 * @author liubin
 * @date 20/1/6 10:14 
 */
@Slf4j
public class ForEachExample {

    private static final List<Integer> list = new ArrayList<>();

    static {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        log.info("list init ...");
    }


    public static void main(String[] args) {
        for3();
    }

    //java.util.ConcurrentModificationException
    private static void for1() {
        for (Integer i : list) {
            list.remove(i);
        }
    }

    //可以remove
    private static void for2() {
        for (int i = 0; i < list.size(); i++) {
            list.remove(list.get(i));
        }
    }

    //可以remove
    private static void for3() {
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int i = it.next();
            System.out.println(i);
            it.remove();
        }
        log.info("" + list.size());
    }

}
