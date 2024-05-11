package com.example.ism.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Aspect
public class TimerAspect {

    private static Long time;
    private static String methodName;

    @Pointcut("execution(public * com.example.ism.serviceimpl.*.*(..))")
    public void controllerPointcut() {}

    @Before("controllerPointcut()")
    public void beforeController(JoinPoint joinPoint) {
        methodName = joinPoint.getSignature().getName();
        time = DateTime.now().getMillis();
        System.out.println("Before method: " + methodName);
    }

    @After ("controllerPointcut()")
    public void afterController() {
        System.out.println("After method: " + methodName + ". Execution time: "
                + (DateTime.now().getMillis() - time) + "ms.");
    }

}
