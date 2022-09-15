package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ParameterAop {

    //적용시킬 위치에 대한 룰을 설정함
    //리턴타입은 상관없고 com.example.aop.controller 하위의 모든 클래스와 모든 메소드이며 파라미터 개수는 상관 없음
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut(){
        //따로 호출되지 않음
        System.out.println("cut method execution");
    }


    //cut() 메소드가 실행전에 시작됨을 명시
    @Before("cut()")
    public void before(JoinPoint joinPoint){
        System.out.println("before method ========================================");
        //JoinPoint? 호출된 메소드의 정보를 알수 있는 인터페이스
        String detailInfo = joinPoint.getSignature().toLongString(); // return type + 호출method(parameter) 정보 출력
        String methodName = joinPoint.getSignature().getName(); // 호출 method 이름만 출력
        StringBuilder sb = new StringBuilder();

        //detail mtehod info : [public] [com.example.aop.dto.User] [com.example.aop.controller.RestApiController.post(com.example.aop.dto.User)]
        //접근제한자 + return type + 호출메소드명(매개변수타입) 이렇게 불러움
        System.out.println("detail mtehod info : " + detailInfo);

        //메소드의 매개변수들의 배열
        Object [] args = joinPoint.getArgs();
        sb.append("method name : "+ methodName).append("\n");
        sb.append("argument list\n");
        for(Object argument : args ){
            sb.append("type : " + argument.getClass().getSimpleName()).append("\n");
            sb.append("value : " + argument).append("\n");
        }

        System.out.println(sb.toString());
    }

    //cut() 메소드가 정상적으로 실행 후에 시작됨을 명시, 메소드의 반환값을 returning으로 받아올 수 있음 (매게변수의 변수명과 일치히야함)
    @AfterReturning(value = "cut()",returning = "returnObject")
    public void afterReturning(JoinPoint joinPoint, Object returnObject){
        System.out.println("after method =========================================");
        System.out.println("return Object : " + returnObject);
    }

}
