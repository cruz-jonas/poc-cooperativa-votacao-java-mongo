package com.example.cooperative.integration.repository;

import com.example.cooperative.integration.model.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository extends MongoRepository<UserEntity, String> {
}
