package ru.gb.inetch.shoppee.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Aspect
@Component
public class ShoppingCardAspect {

    @Pointcut("execution(public * ru.gb.inetch.shoppee.util.ShoppingCart.*(..))")
    public void publicMethods(){}

    @After("publicMethods()")
    public void recalculate(JoinPoint point){
        System.out.println("after add");
        Object targetObject  = point.getTarget();
        try {
            Method m = targetObject.getClass().getMethod("recalculate");
            m.invoke(targetObject);
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        }
    }
}
