package com.example.cooperative.integration.repository;

import com.example.cooperative.integration.model.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMongoRepository extends MongoRepository<UserEntity, String> {
}
