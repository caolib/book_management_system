package com.clb.config;

import com.clb.interceptor.JwtTokenInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * web mvc相关配置
 */

@Configuration
@AllArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private JwtTokenInterceptor jwtTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器对象,不拦截登录注册请求
        registry.addInterceptor(jwtTokenInterceptor)
                .excludePathPatterns(
                        "/reader/login",
                        "/admin/login",
                        "/reader/register",
                        "/admin/register"
                );
    }

}
