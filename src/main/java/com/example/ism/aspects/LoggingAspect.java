package com.example.ism.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Pointcut("@annotation(Log)")
    public void logPointcut(){
    }

    @Around("logPointcut()")
    public Object logAllMethodCallsAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        for (int i=0; i<args.length; i++) {
            if (args[i] == null) {
                System.out.println("Method: " + joinPoint.getSignature().getName() + ", Argument " + i + " is null");
            }
        }
        try{
            return joinPoint.proceed();
        } catch (Exception e) {
            System.out.println("Method: " + joinPoint.getSignature().getName() + "\n Exception: " + e.getMessage());
            throw e;
        }
    }
}