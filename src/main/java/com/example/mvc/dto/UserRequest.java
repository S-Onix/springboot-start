package com.example.mvc.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String email;
    private int age;

    private int test;

    @Override
    public String toString(){
        return name + " / " + email + " / " + age + " / " + test;
    }
}
