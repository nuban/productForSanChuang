package com.bc.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * 将过滤器配置到ioc容器中
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean registrationBean(){ //将注册 过滤器注入
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter((Filter) new LoggingFilter());
        filterRegistrationBean.addUrlPatterns("/shoppingCart/*","/user/orderList","/tempname/*"); // 设置拦截路径
        return filterRegistrationBean;
    }

}
