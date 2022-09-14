package com.example.mvc;

import com.example.mvc.dto.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MvcApplicationTests {

	@Test
	void contextLoads() throws JsonProcessingException {

		//스프링 내부에서 발생하는 Object Mapper 동작 방식을 테스트코드를 이용하여 알아보기

		var objectMapper = new ObjectMapper();

		// object -> text
		// object mapper는 클래스의 get메소드를 통해 데이터를 가져온다.
		// objectMapper가 참조하는 클래스에서 기본 변수 get메소드 외의 get메소드를 만들시 에러 발생

		var user = new User();
		user.setName("tester");
		user.setAge(20);
		user.setPhoneNumber("010-2222-3333");
		var text = objectMapper.writeValueAsString(user);
		System.out.println(text);

		// text -> object
		// default 생성자가 있어야 동작한다.
		var objectUser = objectMapper.readValue(text, User.class);
		System.out.println(objectUser);
	}

}
