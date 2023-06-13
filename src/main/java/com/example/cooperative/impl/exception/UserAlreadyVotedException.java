package com.example.cooperative.impl.exception;

import com.example.cooperative.impl.common.exception.BadRequestException;

public class UserAlreadyVotedException extends BadRequestException {

    public UserAlreadyVotedException(String message) {
        super(message);
    }
}
