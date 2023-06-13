package com.example.cooperative.impl.exception;

import com.example.cooperative.impl.common.exception.BadRequestException;

public class SessionNotFoundException extends BadRequestException {

    public SessionNotFoundException(String message) {
        super(message);
    }

}
