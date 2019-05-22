package com.rickycharles.house.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Configuration用于定义配置类，可替换xml配置文件，被注解的类内部包含有一个或多个被@Bean注解的方法，
 * 这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，
 * 并用于构建bean定义，初始化Spring容器
 */
@Configuration
public class FilterBeanConfig  {

    /**
     * 配置filterBean
     * 1.构造filter
     * 2.配置拦截urlPattern
     * 3.利用FilterRegistrationBean进行包装
     * @return
     */
    @Bean
    public FilterRegistrationBean logFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new LogFilter());
        List<String> urlList = new ArrayList<String>();
        urlList.add("*");
        filterRegistrationBean.setUrlPatterns(urlList);
        return filterRegistrationBean ;
    }
}
