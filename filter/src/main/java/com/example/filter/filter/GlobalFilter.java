package com.example.filter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 주의사항
 * 1. 필터에서 stream을 이용하여 Http 데이터를 읽을시에는 반드시 Caching 가능한 ContentCachingRequestWrapper / ContentCachingResponseWrapper 를 사용하여 진행한다
 * 2. 두 클래스의 생성자는 크기만 지정해주므로 실질적인 내용은 doFiler 메소드 이후에 알 수 있다.
 * 3. ContentCachingResponseWrapper에서 stream을 모두 사용하고 나면 body 값은 비어 있으므로 다시 copy 해준다.
 * */
@Slf4j
@WebFilter(urlPatterns = "/api/user/*")  //특정 클래스에만 filter를 걸고 싶은 경우에 사용 (시작 클래스에도 @ServletComponentScan 를 넣어야함)
public class GlobalFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //전처리
        //Stream으로 읽게 되는 경우 한번 읽고 종료되기 때문에 에러가 발생함
        //HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
        //HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        ContentCachingResponseWrapper contentCachingResponseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);



        //ContentCachingRequestWrapper 생성자에서는 길이만 지정하고 내용은 담고 있지 않는다
        //doFilter를 통해 내부 Spring 안으로 들어가야 내용 Caching 된다.
        chain.doFilter(request, response);

        //후처리
        //request
        String url = contentCachingRequestWrapper.getRequestURI();
        String reqContent = new String(contentCachingRequestWrapper.getContentAsByteArray());
        log.info("request url : {}, request body : {}", url, reqContent);

        //response
        String resContent = new String(contentCachingResponseWrapper.getContentAsByteArray());
        int httpStatus = contentCachingResponseWrapper.getStatus();
        //stream을 다 읽기 때문에 아래 코드가 없이 반환되면 클라이언트에서는 body의 내용이 없다고 인식한다. (다시 복사해서 넣어줌)
        contentCachingResponseWrapper.copyBodyToResponse();
        log.info("response status : {}, response body : {}", httpStatus, resContent);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
