package com.newcoder.community.controller.intercepter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Wenyu Wang
 * @project community
 * @date 2022-07
 * @Description
 */

@Component
public class MasterIntercepter implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(MasterIntercepter.class);

    // Execute before Controller
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("preHandle" + handler.toString());
        return true;//HandlerInterceptor.super.preHandle(request, response, handler);
    }

    // execute after controller
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        logger.debug("postHandle: " + handler.toString());
    }

    // execute after templateEngine
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        logger.debug("afterCompletion: " + handler.toString());
    }
}
