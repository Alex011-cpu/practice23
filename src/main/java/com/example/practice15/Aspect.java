package com.example.practice15;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@org.aspectj.lang.annotation.Aspect
public class Aspect {
    @Around("allServiceMethods()")
    public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object result;
        result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        long methodTime = end - begin;
        log.info("Method: " + joinPoint.getSignature().getName() +
                " from " + joinPoint.getTarget().getClass() +
                " Time: " + methodTime + " ms");
        return result;
    }
    @Pointcut("within(com.example.practice15.services.*)")
    public void allServiceMethods() {}
}
