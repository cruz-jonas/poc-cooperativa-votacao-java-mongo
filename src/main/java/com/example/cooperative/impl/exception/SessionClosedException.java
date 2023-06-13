package com.example.cooperative.impl.exception;

import com.example.cooperative.impl.common.exception.BadRequestException;

public class SessionClosedException extends BadRequestException {

    public SessionClosedException(String message) {
        super(message);
    }
}
