package com.example.exception.advice;

import com.example.exception.controller.ApiController;
import com.example.exception.dto.Error;
import com.example.exception.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

//ApiController 전용 Exception 제어 클래스
@RestControllerAdvice(basePackageClasses = ApiController.class)
public class ApiControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e){
        System.out.println(e.getClass().getName());
        System.out.println(e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    //특정 예외를 잡고 싶을 경우 해당 Exception을 값으로 지정한다.
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        List<Error> errorList = new ArrayList<>();
        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getAllErrors().forEach(error -> {
            FieldError field = (FieldError) error;
            String fieldName = field.getField();
            String msg = field.getDefaultMessage();
            String value = field.getRejectedValue().toString();

            System.out.println("-------------------------");
            System.out.println(fieldName);
            System.out.println(msg);
            System.out.println(value);

            Error errorMsesage = new Error();
            errorMsesage.setField(fieldName);
            errorMsesage.setMessage(msg);
            errorMsesage.setInvalidValue(value);

            errorList.add(errorMsesage);
        });

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setMessage("");
        errorResponse.setRequestUrl(request.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setResultCode("FAIL");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity missingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest request) {
        List<Error> errorList = new ArrayList<>();
        String fieldName = e.getParameterName();
        String fieldType = e.getParameterType();
        String invalidValue = e.getMessage();

        System.out.println(fieldName);
        System.out.println(fieldType);
        System.out.println(invalidValue);

        Error errorMsesage = new Error();
        errorMsesage.setField(fieldName);
        errorMsesage.setMessage(e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setMessage("");
        errorResponse.setRequestUrl(request.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setResultCode("FAIL");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity constraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        List<Error> errorList = new ArrayList<>();
        e.getConstraintViolations().forEach(error -> {

            Stream<Path.Node> stream = StreamSupport.stream(error.getPropertyPath().spliterator(), false);
            List<Path.Node> list = stream.collect(Collectors.toList());

            String fieldName = list.get(list.size()-1).getName();
            String msg = error.getMessage();
            String invalidValue = error.getInvalidValue().toString();

            System.out.println(fieldName);
            System.out.println(msg);
            System.out.println(invalidValue);

            Error errorMsesage = new Error();
            errorMsesage.setField(fieldName);
            errorMsesage.setMessage(msg);
            errorMsesage.setInvalidValue(invalidValue);

            errorList.add(errorMsesage);

        });
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setErrorList(errorList);
            errorResponse.setMessage("");
            errorResponse.setRequestUrl(request.getRequestURI());
            errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
            errorResponse.setResultCode("FAIL");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
