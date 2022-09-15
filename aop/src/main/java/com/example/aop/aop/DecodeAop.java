package com.example.aop.aop;

import com.example.aop.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {

    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut(){

    }

    @Pointcut("@annotation(com.example.aop.annotation.Decode)")
    private void enableDecode(){

    }

    @Before("cut() && enableDecode()")
    public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {
        Object args[] = joinPoint.getArgs();

        for(Object arg : args) {
            if(arg instanceof User) {
                //arg를 User로 형변환
                User user = User.class.cast(arg);
                user.setEmail(new String(Base64.getDecoder().decode(user.getEmail()), "UTF-8"));
            }
        }
    }

    @AfterReturning(value = "cut() && enableDecode()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) throws UnsupportedEncodingException {
        if(returnObj instanceof User) {
            User user = User.class.cast(returnObj);
            user.setEmail(new String(Base64.getEncoder().encodeToString(user.getEmail().getBytes())));
        }
    }
}
