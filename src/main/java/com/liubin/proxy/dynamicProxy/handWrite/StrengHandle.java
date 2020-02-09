package com.liubin.proxy.dynamicProxy.handWrite;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author liubin
 * @description 代理加强
 * @date 2020/2/8 17:47
 */
@Slf4j
public class StrengHandle implements MyInvocationHandler {

    private Object target;

    public StrengHandle(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args){
        log.info("旅行准备工作");
        try {
            method.invoke(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("旅行总结工作");
        return null;
    }
}