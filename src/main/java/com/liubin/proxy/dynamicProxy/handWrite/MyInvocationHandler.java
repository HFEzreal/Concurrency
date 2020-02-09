package com.liubin.proxy.dynamicProxy.handWrite;

import java.lang.reflect.Method;

/**
 * @author liubin
 * @description 手动编写
 * @date 2020/2/8 17:46
 */
public interface MyInvocationHandler {

    Object invoke(Object proxy, Method method, Object[] args);
}