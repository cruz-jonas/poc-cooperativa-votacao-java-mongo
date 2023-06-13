package com.example.cooperative.impl.common.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BadRequestException extends RuntimeException {
    private APIException failedResponse;

    public BadRequestException(String message) {
        this.failedResponse = APIException.builder()
                .message(message)
                .reason(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
    }

    public BadRequestException(String code, String message) {
        this.failedResponse = APIException.builder()
                .code(code)
                .message(message)
                .reason(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
    }
}
