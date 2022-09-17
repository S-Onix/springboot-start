package com.example.springcurltest.controller;

import com.example.springcurltest.component.Calculator;
import com.example.springcurltest.dto.Req;
import com.example.springcurltest.dto.Res;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CaculatorApiController {

    private final Calculator calculator;

    @GetMapping("/sum")
    public int sum(@RequestParam int x, @RequestParam int y){
        return calculator.sum(x,y);
    }


    @PostMapping("/sub")
    public Res sub(@RequestBody Req req){
        int result = calculator.sub(req.getX(),req.getY());

        Res res = new Res();
        res.setResult(result);
        res.setResponse(new Res.Body());

        return res;
    }
}
