package com.cerberus.productservice.exception;

public class ProductNotFountException extends RuntimeException {

    private static final String PRODUCT_MESSAGE = "Товар с %d id не найден.";

    private static final String USER_MESSAGE = "У пользователя нет товаров.";

    public ProductNotFountException(Long id) {
        super(PRODUCT_MESSAGE.formatted(id));
    }

    public ProductNotFountException() {
        super(USER_MESSAGE);
    }
}
