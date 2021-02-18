package ru.gb.inetch.shoppee.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerLogger {
//    @Around("execution(public * ru.gb.inetch.shoppee.controllers.*(..))")
//    public Object loggerAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        MethodSignature sign = (MethodSignature)proceedingJoinPoint.getSignature();
//        System.out.println(sign + " invoked");
//        proceedingJoinPoint.proceed();
//        System.out.println(sign + " finished");
//    }
}
