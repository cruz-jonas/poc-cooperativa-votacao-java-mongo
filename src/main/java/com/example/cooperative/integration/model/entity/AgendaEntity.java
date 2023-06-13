package com.example.cooperative.integration.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Builder
@Data
@Document(collection = "agenda")
public class AgendaEntity {

    private String id;
    private String name;
    private Map<String, Integer> votos;

}
