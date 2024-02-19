package com.clb.util;

import com.clb.constant.Jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;



@Component
@Slf4j
public class JwtUtils {

    /**
     * 生成JWT令牌
     *
     * @param claims JWT第二部分负载 payload 中存储的内容
     */
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, Jwt.SIGNKEY)
                .setExpiration(new Date(System.currentTimeMillis() + Jwt.EXPIRE_TIME))
                .compact();
    }

    /**
     * 解析JWT令牌
     *
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String token) {
        return Jwts.parser()
                .setSigningKey(Jwt.SIGNKEY)//指定签名密钥
                .parseClaimsJws(token)//指定令牌Token
                .getBody();
    }
}
