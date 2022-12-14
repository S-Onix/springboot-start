package com.example.springcurltest.component;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DollarCalculator implements ICalculator {

    private int price;
    private final MarketApi marketApi;

    @Override
    public void init(){
        this.price = marketApi.connect();
    }


    @Override
    public int sum(int x, int y) {
        x *= price;
        y *= price;
        return x + y;
    }

    @Override
    public int sub(int x, int y) {
        x *= price;
        y *= price;
        return x - y;
    }
}
