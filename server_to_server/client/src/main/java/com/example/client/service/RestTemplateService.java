package com.example.client.service;

import com.example.client.dto.Req;
import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class RestTemplateService {

    //요청을 해야함
    //http://localhost/api/server/hello
    //response
    public UserResponse hello(){
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:8090")
                .path("/api/server/hello")
                .queryParam("name","aaa")
                .queryParam("age", 1222)
                .encode()
                .build()
                .toUri();

        log.info(uri.toString());

        //예시 1
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        //예시 2
        ResponseEntity<UserResponse> result2 = restTemplate.getForEntity(uri, UserResponse.class);
        log.info(result2.getStatusCode().toString());
        log.info(result2.getBody().toString());
        log.info(result2.getHeaders().toString());

        return result2.getBody();
    }

    public UserResponse post(){
        //http://localhost:8090/api/server/user/{userId}/name/{userName}

        URI uri = UriComponentsBuilder.fromUriString("http://localhost:8090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "ddddd")   //Path var를 넣어줌 {userId} , {userName}
                .toUri();

        log.info(uri.toString());
        UserRequest request = new UserRequest();
        request.setName("bbbbb");
        request.setAge(1111);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> result = restTemplate.postForEntity(uri,request, UserResponse.class);

        log.info(result.getStatusCode().toString());
        log.info(result.getHeaders().toString());
        log.info(result.getBody().toString());

        return result.getBody();
    }

    public void exchange(){
        //http://localhost:8090/api/server/user/{userId}/name/{userName}

        URI uri = UriComponentsBuilder.fromUriString("http://localhost:8090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "ddddd")   //Path var를 넣어줌 {userId} , {userName}
                .toUri();

        log.info(uri.toString());
        UserRequest request = new UserRequest();
        request.setName("bbbbb");
        request.setAge(1111);

        //Header 설정
        RequestEntity<UserRequest> requestEntity = RequestEntity.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "fffff")
                .body(request);


        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(requestEntity, UserResponse.class);

    }

    public Req<UserResponse> genericExchange(){
        //http://localhost:8090/api/server/user/{userId}/name/{userName}

        URI uri = UriComponentsBuilder.fromUriString("http://localhost:8090")
                .path("/api/server/user/{userId}/name/{userName}/exchange")
                .encode()
                .build()
                .expand(100, "ddddd")   //Path var를 넣어줌 {userId} , {userName}
                .toUri();

        log.info(uri.toString());


        UserRequest userRequest = new UserRequest();
        userRequest.setName("bbbbb");
        userRequest.setAge(1111);

        Req<UserRequest> req = new Req();
        req.setHeader(new Req.Header());
        req.setResponseBody(userRequest);

        //Header 설정
        RequestEntity<Req<UserRequest>> requestEntity = RequestEntity.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "fffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Req<UserResponse>> response
                = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<>(){}); //gneric 타입으로 받는 경우 ParameterizedTypeReference 를 통해 받아온다.

        log.info(response.toString());
        return response.getBody();
    }
}
