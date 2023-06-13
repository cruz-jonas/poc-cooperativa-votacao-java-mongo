package com.example.cooperative.contract.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgendaResponse {

    private UUID id;
    private String name;
    private Map<String, Integer> votos;

}
