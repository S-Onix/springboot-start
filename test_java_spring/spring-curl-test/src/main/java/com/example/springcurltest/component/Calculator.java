package com.example.springcurltest.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Calculator {
    private final ICalculator calculator;

    public int sum(int x, int y){
        calculator.init();
        return calculator.sum(x,y);
    }
    public int sub(int x, int y){
        calculator.init();
        return calculator.sub(x,y);
    }
}
