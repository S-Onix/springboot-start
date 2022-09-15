package com.example.validation.validator;

import com.example.validation.annotation.YearMonth;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class YearMonthValidator implements ConstraintValidator<YearMonth, String> {
    String pattern;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //yyyyMM
        try {
            LocalDate localDate = LocalDate.parse(value+"01", DateTimeFormatter.ofPattern(this.pattern));
        }catch(Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void initialize(YearMonth constraintAnnotation) {
        //어노테이션에 선언된 default 값을 가져옴 yyyyMMdd
        this.pattern = constraintAnnotation.pattern();
    }
}
