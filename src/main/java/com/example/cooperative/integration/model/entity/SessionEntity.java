package com.example.cooperative.integration.model.entity;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Data
@Document(collection = "session")
public class SessionEntity {

    @Id
    private ObjectId id;
    private String agenda;
    private LocalDateTime duration;

}
