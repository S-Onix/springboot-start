package com.example.interceptor.config;

import com.example.interceptor.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor //생성자에서 객체를 주입받음 (순환참조를 막을 수 있다)
public class MvcConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    //인터셉터를 등록해준다
    //인터셉터는 등록된 순서대로 처리되므로 depth 있게 처리가 가능하다.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/api/private/*"); //특정 위치만 검사할 수 있도록함
    }
}
