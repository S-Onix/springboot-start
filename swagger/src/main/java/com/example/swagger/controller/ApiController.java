package com.example.swagger.controller;

import com.example.swagger.dto.UserRequest;
import com.example.swagger.dto.UserResponse;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"API 정보를 제공하는 Controller"}) //swagger page 내의 제목을 변경함
@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    //위에 일괄적 변수에 대한 설명 추가
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x", value = "x 값", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "y", value = "y 값", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/plus/{x}")
    public int plus(
            //@ApiParam(value = "x값") // swagger 내의 변수 설명 및 기본값 설정
            @PathVariable int x,
            //@ApiParam(value = "y값")
            @RequestParam int y){
        return x + y;
    }

    @ApiResponse(code = 502, message = "사용자의 나이가 10살 이하일때")
    @ApiOperation(value = "사용자의 이름과 나이를 리턴하는 메소드")
    @GetMapping("/user")
    public UserResponse user(UserRequest userRequest){
        return new UserResponse(userRequest.getName(), userRequest.getAge());
    }

    @PostMapping("/user")
    public UserResponse userPost(@RequestBody UserRequest userRequest){
        return new UserResponse(userRequest.getName(), userRequest.getAge());
    }
}
