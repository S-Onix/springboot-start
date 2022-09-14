package com.example.mvc.controller;

import com.example.mvc.dto.PutRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PutApiController {

    @PutMapping("/put/{userId}")
    public PutRequestDto put(@RequestBody PutRequestDto request, @PathVariable Long userId) {
        System.out.println(userId);
        return request;
    }


}
