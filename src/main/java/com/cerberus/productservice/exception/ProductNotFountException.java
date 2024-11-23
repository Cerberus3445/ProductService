package com.cerberus.productservice.exception;

public class ProductNotFountException extends RuntimeException {

    private static final String MESSAGE = "Товар с %d id не найден";

    public ProductNotFountException(Long id) {
        super(MESSAGE.formatted(id));
    }
}
