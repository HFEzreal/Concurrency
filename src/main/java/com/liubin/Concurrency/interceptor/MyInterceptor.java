package com.liubin.Concurrency.interceptor;

import com.liubin.Concurrency.threadLocal.ThreadLocalExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description 拦截器
 * @author liubin
 * @date 19/12/27 09:25 
 */
@Slf4j
public class MyInterceptor implements HandlerInterceptor {

    //在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理；
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //添加 threadLocal
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalExample.remove();
        log.info("threadLocal remove ....");
    }
}
