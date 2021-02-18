package ru.gb.inetch.shoppee.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Aspect
@Component
public class ShoppingCartAspect {
    private void recalculateCart(JoinPoint point, HttpSession session) {
        Object targetObject  = point.getTarget();
        try {
            Method m = targetObject.getClass().getMethod("cartRecalculate", HttpSession.class);
            m.invoke(targetObject, session);
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        }
    }

    @After("execution(public * ru.gb.inetch.shoppee.services.ShoppingCartService.addToCart(..))  && args(session, ..)")
    public void addToCard(JoinPoint point, HttpSession session){
        recalculateCart(point, session);
    }

    @After("execution(public * ru.gb.inetch.shoppee.services.ShoppingCartService.removeFromCart(..))  && args(session, ..)")
    public void removeFromCard(JoinPoint point, HttpSession session){
        recalculateCart(point, session);
    }

    @After("execution(public * ru.gb.inetch.shoppee.services.ShoppingCartService.setProductCount(..))  && args(session, ..)")
    public void setProductCount(JoinPoint point, HttpSession session){
        recalculateCart(point, session);
    }

}
