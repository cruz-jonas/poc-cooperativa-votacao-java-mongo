package com.example.cooperative.impl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class SessionClosedRequest {

    private String idSession;
    private String idAgenda;
    private String nameAgenda;
    private LocalDateTime dthrClosed;
    private Map<String, Integer> votos;

}
