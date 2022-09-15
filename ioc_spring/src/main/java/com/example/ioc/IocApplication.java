package com.example.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class IocApplication {

	public static void main(String[] args) {
		SpringApplication.run(IocApplication.class, args);

		//Spring Container(bean Factory)에 등록된 Bean을 AppicationContext를 통해 가져옴
		ApplicationContext context = ApplicationContextProvider.getContext();
		Encoder encoder = context.getBean("base64Encode",Encoder.class);

		System.out.println(encoder.encode("www.naver.com/100?name=test"));

		encoder = context.getBean("urlEncode", Encoder.class);
		System.out.println(encoder.encode("www.naver.com/100?name=test"));

	}

}


//여러가지 bean을 생성하기 위해서 사용되는 annotation
//직접 bean을 등록시킴
@Configuration
class AppConfig{
	//미리 객체를 주입시켜넣음 (DI)
	@Bean("base64Encode")
	public Encoder encoder(Base64Encoder base64Encoder){
		return new Encoder(base64Encoder);
	}

	@Bean("urlEncode")
	public Encoder encoder(UrlEncoder urlEncoder) {
		return new Encoder(urlEncoder);
	}

}