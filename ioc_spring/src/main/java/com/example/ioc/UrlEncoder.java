package com.example.ioc;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;

@Component
public class UrlEncoder implements IEncoder{
    @Override
    public String encode(String message){
        try {
            return URLEncoder.encode(message,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            RuntimeException runtimeException = new RuntimeException(e);
            throw null;
        }
    }
}
