package com.example.ism.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ArgsAspect {

    @Pointcut("@annotation(ArgsLog)")
    public void logPointcut(){
    }

    @Before("logPointcut()")
    public void logAllMethodCallsAdvice(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();

        System.out.println("Method: " + joinPoint.getSignature().getName());
        for (int i=0; i<args.length; i++) {
            System.out.print("Arg " + i + "=" + args[i] + " ");
        }
        System.out.println();
    }
}