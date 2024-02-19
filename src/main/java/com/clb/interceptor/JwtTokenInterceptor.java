package com.clb.interceptor;

import com.clb.constant.Code;
import com.clb.constant.Common;
import com.clb.constant.Excep;
import com.clb.exception.BaseException;
import com.clb.util.JwtUtils;
import com.clb.util.ThreadLocalUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.text.SimpleDateFormat;

/**
 * jwt令牌校验的拦截器
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {
    private final StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 静态资源直接放行
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        //token为空,不放行
        String token = request.getHeader(Common.TOKEN);
        log.debug("token:{}", token);
        if (!StringUtils.hasLength(token)) {
            log.error(Excep.TOKEN_NOT_EXIST);
            response.setStatus(Code.NOT_LOGIN_CODE);
            return false;
        }

        try {
            // 校验redis中的令牌是否过期
            String redisToken = redisTemplate.opsForValue().get(token);
            if (redisToken == null) {
                throw new BaseException(Excep.TOKEN_ALREADY_EXPIRED);
            }
            //解析
            Claims claims = JwtUtils.parseJWT(token);
            // 保存用户信息
            ThreadLocalUtil.set(claims);
            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getExpiration());
            log.debug("id:{},username:{},令牌到期时间:{}", claims.get(Common.ID), claims.get(Common.USERNAME), format);
        } catch (Exception e) {
            //解析失败不放行
            String message = e.getMessage();
            log.error("非法令牌 " + message);
            if (message.contains("expire")) {
                log.error("令牌过期!");
            }
            response.setStatus(Code.IDENTITY_EXPIRES);
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 请求完成后清除ThreadLocal中的数据
        ThreadLocalUtil.remove();
    }
}

