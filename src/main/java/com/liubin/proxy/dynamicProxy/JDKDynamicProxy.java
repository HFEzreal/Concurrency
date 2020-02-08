package com.liubin.proxy.dynamicProxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author liubin
 * @description jdk动态代理
 * @date 2020/2/8 14:00
 */
@Slf4j
public class JDKDynamicProxy implements InvocationHandler {

    private Object object;

    public JDKDynamicProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("旅行准备工作");
        Object result = method.invoke(object, args);
        log.info("旅行总结工作");
        return result;
    }
}