package com.example.springcurltest.controller;

import com.example.springcurltest.component.Calculator;
import com.example.springcurltest.component.DollarCalculator;
import com.example.springcurltest.component.MarketApi;
import com.example.springcurltest.dto.Req;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CaculatorApiController.class) // Unit Test (웹에 특화된 객체만 로딩됨 -> 부하를 줄일 수 있음)
@AutoConfigureWebMvc // MVC와 관련된 Bean을 올린다.
@Import({Calculator.class, DollarCalculator.class})
public class CalculatorApiTest {

    @MockBean
    private MarketApi marketApi;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init(){
        Mockito.when(marketApi.connect()).thenReturn(3000);
    }

    @Test
    public void sumTest(){
        //http://localhost:8080/api/sum

        try {
            mockMvc.perform(
                    MockMvcRequestBuilders.get("http://localhost:8080/api/sum")
                            .queryParam("x","10")
                            .queryParam("y", "10")
            ).andExpect(
                    MockMvcResultMatchers.status().isOk()
            ).andExpect(
                    MockMvcResultMatchers.content().string("60000")
            ).andDo(MockMvcResultHandlers.print());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void subTest() throws JsonProcessingException {


        //Request를 임의로 만들어준 후 json 형태로 변환
        Req req = new Req();
        req.setX(10);
        req.setY(10);
        String json = new ObjectMapper().writeValueAsString(req);

        try {
            mockMvc.perform(
                MockMvcRequestBuilders.post("http://localhost:8080/api/sub")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
            ).andExpect(
                    MockMvcResultMatchers.status().isOk()
            ).andExpect(
                    //response를 받을 객체의 필드들을 정의하고 예측값을 작성
                    MockMvcResultMatchers.jsonPath("$.result").value("0")
            ).andExpect(
                    MockMvcResultMatchers.jsonPath("$.response.resultCode").value("OK")
            ).andDo(MockMvcResultHandlers.print());
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
