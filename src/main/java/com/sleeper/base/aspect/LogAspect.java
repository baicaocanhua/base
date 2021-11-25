package com.sleeper.base.aspect;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志切面
 * @author sleeper
 */
@Slf4j
@Aspect
@Component
// @Profile({"dev", "test"})
public class LogAspect {

    /** 以自定义 @WebLog 注解为切点 */
    @Pointcut("@annotation(com.sleeper.base.annotate.WebLog)")
    public void webLog() {}

    /**
     * 在切点之前织入
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("IP:{}  Class Method:{}.{}   Request Args: {}",request.getRemoteAddr(),joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), new Gson().toJson(joinPoint.getArgs()));
    }

    /**
     * 响应
     * @return Object
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        log.info("Response Args  : {} Time-Consuming : {} ms", new Gson().toJson(result),System.currentTimeMillis() - startTime);
        return result;
    }

}
