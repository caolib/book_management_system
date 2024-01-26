package com.clb.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class Advice {
    //匹配controller、service和mapper包下所有方法,返回值任意
    @Pointcut("execution(* com.clb.service.*.*(..))" +
            "||execution(* com.clb.controller.*.*(..))" +
            "||execution(* com.clb.mapper.*.*(..))")
    public void pointcut() {
    }

    //记录方法执行耗时ms
    @Around("pointcut()")
    public Object logAdvice(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        Object result;
        String name = joinPoint.getSignature().toString();
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        long time = System.currentTimeMillis() - start;

        log.debug("[耗时:{}ms {}]", time, name);

        return result;
    }
}
