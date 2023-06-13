package com.example.cooperative.integration.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Data
@Document(collection = "session")
public class SessionEntity {

    private String id;
    private String agenda;
    private Integer duration;
    private LocalDateTime createdAt;

}
