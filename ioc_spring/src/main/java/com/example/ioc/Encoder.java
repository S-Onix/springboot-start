package com.example.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class Encoder {
    IEncoder encoder;

    public Encoder(IEncoder encoder){
        this.encoder = encoder;
    }

    public void setIEncoder(IEncoder encoder) {
        this.encoder = encoder;
    }

    public String encode(String message){
        return encoder.encode(message);
    }
}
