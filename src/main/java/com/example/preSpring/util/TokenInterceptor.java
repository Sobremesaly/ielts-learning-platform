package com.example.preSpring.util;

import com.example.preSpring.tools.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义token拦截器
 *
 * @author 小叶子
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Resource
    TokenUtil tokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");

        if (token != null) {
            boolean result = tokenUtil.verify(token);
            if (result) {
                System.out.println("通过拦截器");
                return true;
            }
        }
        System.out.println("认证失败");
        response.setStatus(504);
        response.getWriter().write("50000");
        return false;
    }
}