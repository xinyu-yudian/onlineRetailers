package com.xinyu.utils;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class HeaderTokenInterceptor implements HandlerInterceptor {

    private static final Logger LOG = Logger.getLogger(HeaderTokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        // 获取请求头中的token验证字符串
        String headerToken = request.getHeader("token");
        System.out.println("headerToken: " + headerToken);
        // 检测当前页面，设置当前页不是登录页面时就对其进行拦截
        // 具体方法就是检测URL中有没有login字符串
        if (!request.getRequestURI().contains("login")) {
            if (headerToken == null) {
                // 如果没有token，返回错误信息
                System.out.println("token不存在");
            }
            try {
                // 对token更新与验证
                Map<String,Object> claims= JWTUtils.parseToken(headerToken);
                String name = (String) claims.get("username");
                System.out.println(name);
                LOG.debug("token验证通过");
            } catch (Exception e) {
                //如果验证不合法，直接返回状态码401，返回失败信息
                response.setStatus(401);
                response.getWriter().write("{\"message\":\"forbidden\"}");
                return false;
            }
        }
        return true;
    }
}

