package com.pb.hiring.controllers.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserRequestContextHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private UserRequestContext userRequestContext;
    
    @Override
    public void afterCompletion(HttpServletRequest req,
            HttpServletResponse resp, Object handler, Exception exp)
            throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse resp,
            Object handler, ModelAndView mav) throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,
            Object handler) throws Exception {
        userRequestContext.setUserEmail( req.getUserPrincipal().getName() );
        return true;
    }
}
