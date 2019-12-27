package com.liubin.Concurrency.interceptor;

import com.liubin.Concurrency.threadLocal.ThreadLocalExample;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.FilterConfig;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @description 过滤器
 * @author liubin
 * @date 19/12/27 09:40 
 */
@Slf4j
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("MyFilter init ...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        log.info("threadId：{},{}", Thread.currentThread().getId(), httpServletRequest.getServletPath());
        ThreadLocalExample.add(Thread.currentThread().getId());
        log.info("MyFilter doFilter ...");
        //跳转
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("MyFilter destroy ...");
    }
}
