package com.example.cooperative.impl.exception;

import com.example.cooperative.impl.common.exception.BadRequestException;

public class TipoVotoInvalidoException extends BadRequestException {

    public TipoVotoInvalidoException(String message) {
        super(message);
    }
}
