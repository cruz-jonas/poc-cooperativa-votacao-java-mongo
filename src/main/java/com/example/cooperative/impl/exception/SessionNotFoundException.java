package com.example.cooperative.impl.exception;

import org.apache.kafka.common.errors.ApiException;

public class SessionNotFoundException extends ApiException {

    public SessionNotFoundException(String message) {
        super(message);
    }

}
