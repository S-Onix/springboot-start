package org.example;

public class Main {
    public static void main(String[] args) {
        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";

        // Base 64로 인코딩
        // URL 인토딩
        IEncoder encode = new Base64Encoder();
        String result = encode.encode(url);
        System.out.println(result);

        IEncoder urlEncoder = new UrlEncoder();
        String result2 = urlEncoder.encode(url);
        System.out.println(result2);

        //외부에서 사용하는 객체를 주입받는 것을 DI라고 한다.
        //근본 본질의 class 내부는 건드리지 않음
        Encoder encoder = new Encoder(new UrlEncoder());
        System.out.println(encoder.encode(url));


    }
}