package com.liubin.Concurrency.publish;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @description 不安全发布对象
 * @author liubin
 * @date 19/12/24 16:02 
 */
@Slf4j
public class UnPublishExample {

    private UnPublishExample(){
    }

    private String[] array = new String[]{"a","b","c"};

    private String[] getArray(){
        return this.array;
    }

    public static void main(String[] args) {
        UnPublishExample example = new UnPublishExample();
        log.info(Arrays.toString(example.array));
        example.getArray()[0] = "1231";
        log.info(Arrays.toString(example.array));
    }

}
