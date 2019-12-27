package com.liubin.Concurrency.interceptor;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description 过滤器配置
 * @author liubin
 * @date 19/12/27 10:09 
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.addUrlPatterns("/test");
        return filterRegistrationBean;
    }
}
