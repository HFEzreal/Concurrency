package com.liubin.proxy.dynamicProxy;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author liubin
 * @description cglib动态代理
 * @date 2020/2/8 15:05
 */
@Slf4j
public class CglibProxyInterceptor implements MethodInterceptor {

    /**
     * @description cglib拦截
     * @author liubin
     * @date 2020/2/8 15:26
     * @param object 表示增强的对象
     * @param method 表示要被拦截的方法
     * @param args 被拦截方法的参数
     * @param proxy 触发父类的方法对象
     * @return java.lang.Object
     */
    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        log.info("旅行准备工作");
        Object result = proxy.invokeSuper(object, args);
        log.info("旅行总结工作");
        return result;
    }
}