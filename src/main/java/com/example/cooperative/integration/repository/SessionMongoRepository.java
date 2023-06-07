package com.example.cooperative.integration.repository;

import com.example.cooperative.integration.model.entity.SessionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionMongoRepository extends MongoRepository<SessionEntity, String> {
}
