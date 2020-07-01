package com.wuwind.interceptor;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInterceptor implements HandlerInterceptor {
    private static Logger logger = Logger.getLogger(LogInterceptor.class);
    private static final ThreadLocal<Long> startTimeThreadLocal =
            new NamedThreadLocal<Long>("ThreadLocal StartTime");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        long beginTime = System.currentTimeMillis();//1、开始时间
        startTimeThreadLocal.set(beginTime);        //线程绑定变量（该数据只有当前请求的线程可见）
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
//        if (modelAndView != null) {
//            logger.info("ViewName: " + modelAndView.getViewName());
//        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
//        long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
//        long endTime = System.currentTimeMillis();  //2、结束时间
        MDC.put("url", request.getRequestURI());
        logger.info(request.getRequestURI());
    }

}