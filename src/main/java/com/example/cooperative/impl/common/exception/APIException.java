package com.example.cooperative.impl.common.exception;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Builder
public class APIException {
    private String code;
    private String reason;
    private String message;
    @Builder.Default
    private Collection<APIException> errors = new ArrayList<>();

}
