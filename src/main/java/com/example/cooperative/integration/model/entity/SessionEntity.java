package com.example.cooperative.integration.model.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "session")
public class SessionEntity {

    @Id
    private ObjectId id;

}
