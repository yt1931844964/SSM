package com.qtds.config;

import com.qtds.util.SysAdminUtil;
import com.qtds.entity.SysAdmin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SysAdmin loginUser = SysAdminUtil.getLoginUser(request.getSession());
        if (loginUser == null || loginUser.getLoginName() == null) {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }

    // Controller执行之后,返回ModelAndView之前
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    // 视图返回之后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
