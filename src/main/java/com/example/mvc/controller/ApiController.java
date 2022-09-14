package com.example.mvc.controller;

import com.example.mvc.dto.User;
import com.example.mvc.dto.UserReq;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping
    public UserReq user(UserReq userReq) {
        return userReq;
    }

    @GetMapping("/hello")   // http://localhost:8080/api/hello
    public String hello(){
        return "Hello World";
    }

    //response 방법들
    //text
    @GetMapping("/text")
    public String text(@RequestParam String account){
        return account;
    }

    //json
    //아래 일련의 과정을 통해 json to object / object to json 으로 데이터타입이 변경된다
    //request -> object Mapper -> object -> 아래 mehtod 진입 -> object -> object Mapper -> json -> response
    @PostMapping("/json")
    public User json(@RequestBody User user){
        return user;
    }

    //put의 경우 결과 코드 201과 200을 동시에 사용한다 (생성과 수정)
    //응답 객체의 커스터마이징이 필요한 경우 RespnoseEntity로 리턴해준다.
    @PutMapping("/put")
    public ResponseEntity<User> put(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
