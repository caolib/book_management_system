package com.clb.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.clb.constant.Common;
import com.clb.constant.Excep;
import com.clb.domain.Result;
import com.clb.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.text.SimpleDateFormat;

// todo 登录校验

/**
 * jwt令牌校验的拦截器
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他静态资源
        if (!(handler instanceof HandlerMethod)) {
            //静态资源直接放行
            return true;
        }
        //1.获取请求头中的token
        String token = request.getHeader(Common.TOKEN);
        //2.如果token为空，返回未登录的错误信息
        if (!StringUtils.hasLength(token)) {
            log.error(Excep.TOKEN_NOT_EXIST);
            //创建响应结果对象
            Result<String> responseResult = Result.error(401,Excep.NOT_LOGIN);
            //把Result对象转换为JSON格式字符串
            String json = JSONObject.toJSONString(responseResult);
            //设置响应头,告知浏览器：响应的数据类型为json、响应的数据编码表为utf-8
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(401);
            //响应
            response.getWriter().write(json);
            //不放行
            return false;
        }
        //3.解析token，如果解析失败，返回未登录的错误信息
        try {
            Claims claims = JwtUtils.parseJWT(token);
            String ttl = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getExpiration());
            log.info("令牌到期时间:{}", ttl);
        }catch (Exception e){
            log.error("<令牌异常! -> 无效/过期>");
            //创建响应结果对象
            Result<Object> responseResult = Result.error(419,Excep.NOT_LOGIN);
            //把Result对象转换为JSON格式字符串
            String json = JSONObject.toJSONString(responseResult);
            //设置响应头
            response.setContentType("application/json;charset=utf-8");
            //响应
            response.getWriter().write(json);
            response.setStatus(419);
            //不放行
            return false;
        }

        //4.放行
        return true;
    }
}

