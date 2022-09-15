package com.example.aop.dto;

import lombok.Data;

@Data
public class User {

    private String id;
    private String pw;
    private String email;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
