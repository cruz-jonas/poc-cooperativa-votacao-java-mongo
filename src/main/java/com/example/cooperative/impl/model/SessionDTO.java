package com.example.cooperative.impl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class SessionDTO {

    private String id;
    private String idAgenda;
    private Integer duration;
    private LocalDateTime createdAt;

}
