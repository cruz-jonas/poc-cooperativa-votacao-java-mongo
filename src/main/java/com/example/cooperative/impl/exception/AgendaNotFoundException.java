package com.example.cooperative.impl.exception;

import com.example.cooperative.impl.common.exception.BadRequestException;

public class AgendaNotFoundException extends BadRequestException {

    public AgendaNotFoundException(String message) {
        super(message);
    }

}
