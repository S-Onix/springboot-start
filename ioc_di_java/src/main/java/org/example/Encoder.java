package org.example;

public class Encoder {
    IEncoder encoder;

    public Encoder(IEncoder encoder){
        this.encoder = encoder;
    }

    public String encode(String message){
        return encoder.encode(message);
    }
}
