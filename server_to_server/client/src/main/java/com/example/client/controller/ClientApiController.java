package com.example.client.controller;

import com.example.client.dto.Req;
import com.example.client.dto.UserResponse;
import com.example.client.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ClientApiController {
    @Autowired
    private RestTemplateService restTemplateService;

    @GetMapping("/hello")
    public UserResponse getHello(){
        return restTemplateService.hello();
    }

    @PostMapping("/post")
    public UserResponse post(){
        return restTemplateService.post();
    }

    @PostMapping("/exchange")
    public void exchange(){
        restTemplateService.exchange();
    }

    @PostMapping("/generic-exchange")
    public Req<UserResponse> genericExchange(){
        return restTemplateService.genericExchange();
    }
}

