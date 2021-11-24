package com.github.martynfunclub.trackingsystem.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class LogoutInterceptor implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (request.isUserInRole("ROLE_WORKER")) {
            Cookie[] cookies = request.getCookies();
            Cookie deleteCookie;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    deleteCookie = cookie;
                    deleteCookie.setMaxAge(1);
                    response.addCookie(deleteCookie);
                    break;
                }
            }
        }
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
