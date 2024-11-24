package com.cerberus.productservice.controller;

import com.cerberus.productservice.exception.ProductNotFountException;
import com.cerberus.productservice.exception.ProductValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(ProductNotFountException.class)
    public ProblemDetail handleException(ProductNotFountException exception){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Товар не найден");
        problemDetail.setDetail(exception.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(ProductValidationException.class)
    public ProblemDetail handleException(ProductValidationException exception){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Ошибка валидации");
        problemDetail.setDetail(exception.getMessage());
        return problemDetail;
    }
}
