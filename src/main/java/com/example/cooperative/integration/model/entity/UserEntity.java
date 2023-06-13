package com.example.cooperative.integration.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Data
@Document(collection = "user")
public class UserEntity {

    private String id;
    private String name;
    private List<String> votedAgendas;

}
