package com.example.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


/**
 * @Bean과 @Component의 차이
 * @Bean의 경우 클래스에 붙이지 못함 단, 메소드에 붙일 수 잇음
 * @Configuration 이 붙어있는 클래스 내에 여러개의 @Bean을 등록할 수 있음
 * */

@Aspect
@Component
public class TimerAop {
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut(){

    }

    //@Timer가 선언된곳에서 실행됨
    @Pointcut("@annotation(com.example.aop.annotation.Timer)")
    private void enableTimer(){

    }

    @Around("cut() && enableTimer()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around method =====================================");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();
        System.out.println("total time : " + stopWatch.getTotalTimeSeconds());

    }
}
