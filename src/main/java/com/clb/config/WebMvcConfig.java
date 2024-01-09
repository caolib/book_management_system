package com.clb.config;

import com.clb.interceptor.JwtTokenInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private JwtTokenInterceptor jwtTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器对象,拦截业务请求，不拦截登录注册请求
        registry.addInterceptor(jwtTokenInterceptor)
                .addPathPatterns("/book")
                .addPathPatterns("/borrow")
                .excludePathPatterns("/login","/register");
    }

    //允许所有跨域请求
    //@Override
    //public void addCorsMappings(CorsRegistry registry) {
    //    registry.addMapping("/**")
    //            .allowedOriginPatterns("*")
    //            .allowedMethods("*")
    //            .allowedHeaders("*")
    //            .allowCredentials(true);
    //}
}
