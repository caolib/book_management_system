package com.clb.controller;

import com.clb.constant.Common;
import com.clb.domain.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommonController {

    private final StringRedisTemplate redisTemplate;

    @DeleteMapping("/logout")
    public Result<String> logout(@RequestHeader(Common.TOKEN) String token) {
        log.debug("token:{}", token);

        redisTemplate.delete(token);
        return Result.success();
    }

}
