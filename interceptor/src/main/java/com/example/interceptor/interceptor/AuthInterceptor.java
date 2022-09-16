package com.example.interceptor.interceptor;

import com.example.interceptor.annotation.Auth;
import com.example.interceptor.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();

        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI())
                .query(request.getQueryString()) // request 쿼리를 가져옴
                .build()
                .toUri();

        log.info("request url : {}" , url);
        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("has annotation : {}" , hasAnnotation);

        // Auth 권한을 가진 요청에 대해서는 세션과 쿠키를 저장한다.
        if(hasAnnotation) {
            // 권한 체크
            String query = uri.getQuery();
            log.info("query : {}", query);

            if(query.equals("name=test"))
                return true;

            throw new AuthException();

        }

        return true;
    }

    private boolean checkAnnotation(Object handler, Class clazz){
        // resource에 대한 요청일 경우 그냥 통과
        if(handler instanceof ResourceHttpRequestHandler)
            return true;

        // annotation check
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if(handlerMethod.getMethodAnnotation(clazz) != null || handlerMethod.getBeanType().getAnnotations() != null) {
            return true;
        }

        return false;
    }
}
