package com.classpath.ordermgmt.exception;

public class OrderLineItemNotFoundException extends Exception {

    public OrderLineItemNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}