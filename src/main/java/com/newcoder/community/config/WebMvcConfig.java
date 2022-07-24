package com.newcoder.community.config;

import com.newcoder.community.controller.intercepter.LoginTicketIntercepter;
import com.newcoder.community.controller.intercepter.MasterIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Wenyu Wang
 * @project community
 * @date 2022-07
 * @Description
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private MasterIntercepter masterIntercepter;

    @Autowired
    private LoginTicketIntercepter loginTicketIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(masterIntercepter)
                .excludePathPatterns("/**/*.css","/**/*.js","/**/*.png","/**/*.jpeg")
                .addPathPatterns("/register", "/login");

        registry.addInterceptor(loginTicketIntercepter)
                .excludePathPatterns("/**/*.css","/**/*.js","/**/*.png","/**/*.jpeg");
    }
}
