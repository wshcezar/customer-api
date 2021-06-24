package com.customer.api.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

    private Serializable key;

    public ResourceNotFoundException(String message) { super(message); }

    public ResourceNotFoundException(String message, Serializable key) {
        super(message);
        this.key = key;
    }
}