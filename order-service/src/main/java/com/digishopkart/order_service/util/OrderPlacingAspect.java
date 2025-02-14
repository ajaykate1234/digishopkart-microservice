package com.digishopkart.order_service.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


// This is just added for AOP understanding but not working correctly
@Aspect
@Component
public class OrderPlacingAspect {
    // Before Advice: Runs before the method execution
    @Before("execution(* com.digishopkart.order_service.services.OrderService.placeOrderService(..))")
    public void beforeAdvice(){
        System.out.println("Before creating order");
    }

    // After Advice: Runs after the method execution
    @After("execution(* com.digishopkart.order_service.services.OrderService.placeOrderService(..))")
    public void afterAdvice(){
        System.out.println("After Creating order");
    }

    // Around Advice: Runs before and after method execution
    @Around("execution(* com.digishopkart.order_service.services.OrderService.placeOrderService(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before Advice : "+joinPoint.getSignature());

        Object result = joinPoint.proceed();

        System.out.println("After Advice : "+joinPoint.getSignature());

        return result;
    }
}
