package com.example.cooperative.impl.exception;

import org.apache.kafka.common.errors.ApiException;

public class SessionClosedException extends ApiException {

    public SessionClosedException(String message) {
        super(message);
    }
}
