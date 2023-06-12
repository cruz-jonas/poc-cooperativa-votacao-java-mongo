package com.example.cooperative.integration.model.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "user")
public class UserEntity {

    @Id
    private ObjectId id;
    private List<AgendaEntity> votedAgendas;

}
