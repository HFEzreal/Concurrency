package com.liubin.proxy;

import com.liubin.proxy.dynamicProxy.JDKDynamicProxy;
import com.liubin.proxy.staticProxy.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author liubin
 * @description 测试类
 * @date 2020/2/8 12:05
 */
public class Test {

    public static void main(String[] args) {
        //1.静态代理-继承代理
//        extendsProxy();
        //2.静态代理-聚合代理
//        implementsProxy();
        //3.动态代理-jdk动态代理
        jdkDynamicProxy();
        //4.动态代理-CGLIB动态代理


    }

    //静态代理-继承代理
    public static void extendsProxy() {
        Car car = new CarChild();
        car.travel();
    }

    //静态代理-聚合代理
    public static void implementsProxy() {
        Travel travel = new TravelProxy(new Train());
        travel.travel();
    }

    //动态代理-jdk动态代理
    public static void jdkDynamicProxy() {
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Train travel = new Train();
        InvocationHandler handle = new JDKDynamicProxy(travel);
        //当使用非接口类强转时，会报 ClassCastException 错误，所以JDK动态代理只能代理接口类
        // Exception in thread "main" java.lang.ClassCastException: com.sun.proxy.$Proxy0 cannot be cast to com.liubin.proxy.staticProxy.Train
//        Train travel1 = (Train)Proxy.newProxyInstance(travel.getClass().getClassLoader(), travel.getClass().getInterfaces(), handle);
        Travel travel1 = (Travel) Proxy.newProxyInstance(travel.getClass().getClassLoader(), travel.getClass().getInterfaces(), handle);
        travel1.travel();
    }

}