package com.example.springcurltest.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest // 실제 어플리케이션을 실행하여 테스트한다 (통합테스트시 사용)
public class DollarCalculatorTest {

    @MockBean
    private MarketApi marketApi;

    @Autowired
    private Calculator calculator;

    @Test
    public void dallarCalculatorTest(){
        Mockito.when(marketApi.connect()).thenReturn(3000);
        int sum = calculator.sum(10,10);
        int sub = calculator.sub(10,10);

        Assertions.assertEquals(60000, sum);
        Assertions.assertEquals(0, sub);
    }
}
