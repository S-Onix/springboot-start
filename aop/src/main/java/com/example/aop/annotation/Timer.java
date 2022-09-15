package com.example.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.TYPE, ElementType.METHOD}) //해당 Annotation이 어디에 작성될 수 있는지 명시해주는 Annotation
@Retention(RetentionPolicy.RUNTIME)  //어떤 시점까지 해당 Annotation의 메모리를 보유하고 있을지 명시해주는 Annotation
public @interface Timer {
}
