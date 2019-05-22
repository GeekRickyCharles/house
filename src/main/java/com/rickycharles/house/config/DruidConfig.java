package com.rickycharles.house.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Druid连接池配置类
 * @author RickyCharles
 */
@Configuration
public class DruidConfig {

    /**
     * 这个注解可以将bean方法里面的返回对象与外部的配置对象进行绑定
     * 参见application.properties
     * @return
     */
    @ConfigurationProperties(prefix = "spring.druid")


    /**
     * 创建DruidDataSource数据源
     * SpringBoot容器在创建的时候，通过Bean注解到容器中，通过initMethod方法执行Druid中的初始化相关方法
     * 容器在销毁的时候，通过执行druid中的close方法，关闭数据源，释放连接
     * @return
     */
    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setProxyFilters(Lists.newArrayList(statFilter()));
        return druidDataSource;
    }

    /**
     * 打印慢查询sql日志,Filter是alibaba.druid下的filter
     * @return
     */
    @Bean
    public Filter statFilter(){
        StatFilter statFilter = new StatFilter();
        //设置慢日志的时间,超过5s的sql为满查询sql
        statFilter.setSlowSqlMillis(5000);
        //是否打印慢日志
        statFilter.setLogSlowSql(true);
        //是否合并慢日志
        statFilter.setMergeSql(true);
        return statFilter;
    }

    //添加监控
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    }

}
