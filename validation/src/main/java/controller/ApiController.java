package controller;

import dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/user")
    public ResponseEntity user(@Valid @RequestBody User user, BindingResult bindingResult){

        //Validation Annotaion을 사용하지 않을 경우 변수가 사용되는 지점마다 검증코드가 들어가야한다.
        /*if(user.getAge() >= 90) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
        }*/

        // 검증과정에서 검증에 통과하지 못한 경우 예외가 터지는 것이 아니라 결과가 BindingResult에 들어가게됨
        // 에러가 발생한 필드와 에러메세지를 가져올 수 있다.
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String message = objectError.getDefaultMessage();

                sb.append("field : " + field.getField());
                sb.append(message);
            });

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }

        return ResponseEntity.ok(user);
    }
}
