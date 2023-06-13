package com.example.cooperative.impl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class AgendaDTO {

    private String id;
    private String name;
    private Map<String, Integer> votos;

}
