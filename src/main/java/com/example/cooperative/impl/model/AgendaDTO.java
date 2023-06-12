package com.example.cooperative.impl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class AgendaDTO {

    private ObjectId id;
    private String name;
    private Map<String, Integer> votos;

}
