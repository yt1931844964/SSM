package com.qtds.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册Interceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new AdminInterceptor());
        //拦截所有路径
        registration.addPathPatterns("/**");
        //添加不拦截路径
        registration.excludePathPatterns(
                "/",
                "/loginPage",
                "/login",

                "/css/**",
                "/fonts/**",
                "/images/**",
                "/js/**",
                "/lib/**"
        );
    }
}
