package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/server")
@RequiredArgsConstructor
@Slf4j
public class ServerApiController {

    @GetMapping("/hello")
    public User hello(@RequestParam String name, @RequestParam int age){
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @PostMapping("/user/{userId}/name/{userName}")
    public User post(@RequestBody User user,
                     @PathVariable Integer userId,
                     @PathVariable String userName,
                     @RequestHeader("x-authorization") String authorization,
                     @RequestHeader("custom-header") String custom
                     ){

        log.info("userId : {} , userName : {}", userId, userName);
        log.info("client request : {}", user);
        log.info("authorization : {} , custom : {}", authorization, custom);
        user.setName(userName);
        user.setAge(userId);
        return user;
    }
    @PostMapping("/user/{userId}/name/{userName}/exchange")
    public Req<User> exchange(
                     @RequestBody Req<User> user,
                     @PathVariable Integer userId,
                     @PathVariable String userName,
                     @RequestHeader("x-authorization") String authorization,
                     @RequestHeader("custom-header") String custom
    ){
        log.info("userId : {} , userName : {}", userId, userName);
        log.info("client request : {}", user);
        log.info("authorization : {} , custom : {}", authorization, custom);

        Req<User> response = new Req<>();
        response.setHeader(new Req.Header());
        response.setResponseBody(user.getResponseBody());

        log.info("server response : {}", response);
        return response;
    }
}
