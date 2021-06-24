package com.customer.api.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidatorCustomerException extends RuntimeException {

    private String messageCustom;

    public ValidatorCustomerException(String message, String messageCustom) {
        super(message);
        this.messageCustom = messageCustom;
    }
}