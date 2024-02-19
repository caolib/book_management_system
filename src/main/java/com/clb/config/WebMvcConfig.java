package com.clb.config;

import com.clb.interceptor.JwtTokenInterceptor;
import com.clb.interceptor.LoggingInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * web mvc相关配置
 */

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final JwtTokenInterceptor jwtTokenInterceptor;
    private final LoggingInterceptor loggingInterceptor;

    /**
     * 添加拦截器，order越小，拦截器的优先级越高
     * @param registry 注册对象
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器对象,不拦截登录注册请求
        registry.addInterceptor(jwtTokenInterceptor)
                .excludePathPatterns("/**/login", "/**/register","/error","/**logout")
                .order(10);

        registry.addInterceptor(loggingInterceptor)
                .order(0);

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("*")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true)
                .maxAge(3600);
    }

}
