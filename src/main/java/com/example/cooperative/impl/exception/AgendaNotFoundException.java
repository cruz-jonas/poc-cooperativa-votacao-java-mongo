package com.example.cooperative.impl.exception;

import org.apache.kafka.common.errors.ApiException;

public class AgendaNotFoundException extends ApiException {

    public AgendaNotFoundException(String message) {
        super(message);
    }

}
