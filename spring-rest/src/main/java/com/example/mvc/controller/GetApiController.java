package com.example.mvc.controller;

import com.example.mvc.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping(path = "/hello")   //http://localhost:8080/api/get/hello
    public String getHello(){
        return "get Hello";
    }

    //@GetMapping 이전 주소를 매핑할 때 사용되었던 방법
    @RequestMapping(method = RequestMethod.GET, path = "/hi") //http://localhost:8080/api/get/hi
    public String hi(){
        return "hi";
    }

    @GetMapping("/path-variable/{name}")        //http://localhost:8080/api/get/path-variable/{name}
    public String pathVariable(@PathVariable(name = "name") String pathName, String name){
        System.out.println("PathVariable : " + pathName);
        return pathName;
    }

    @GetMapping("/query-param")     //http://localhost:8080/api/get/query-param?name=&email=&age=
    public String queryParam(@RequestParam Map<String, String> queryParam){
        StringBuilder sb = new StringBuilder();
        queryParam.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " : " + entry.getValue());
            sb.append(entry.getKey() + " : " + entry.getValue() + "\n");
        });

        return sb.toString();
    }

    @GetMapping("/query-param02")   //http://localhost:8080/api/get/query-param?name=&email=&age=
    public String queryParam02(@RequestParam String name, @RequestParam String email, @RequestParam int age) {
        return name + " " + email + " " + age;
    }

    @GetMapping("/query-param03")
    public String queryParam03(UserRequest userRequest) {
        return userRequest.toString();
    }
}
