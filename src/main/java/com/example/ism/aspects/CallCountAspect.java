package com.example.ism.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Aspect
public class CallCountAspect {

    private static final HashMap<String, Integer> callCount = new HashMap<>();

    private static int incrementCallCount(String methodName) {
        if (callCount.containsKey(methodName)) {
            callCount.put(methodName, callCount.get(methodName) + 1);
            return callCount.get(methodName);
        }

        callCount.put(methodName, 1);
        return 1;
    }

    @Pointcut("execution(public * com.example.ism.serviceimpl.*.*(..))")
    public void controllerPointcut() {}

    @After ("controllerPointcut()")
    public void afterController(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        int counter = incrementCallCount(methodName);
        System.out.println("Called method: " + methodName + ". Count: " + counter + ".");
    }

}
