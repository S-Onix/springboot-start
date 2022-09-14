package com.example.mvc.controller;

import com.example.mvc.dto.PostRequestDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostApiController {
    @PostMapping("/post")
    public void post(@RequestBody PostRequestDto requestDto){
        System.out.println(requestDto);
    }
}
