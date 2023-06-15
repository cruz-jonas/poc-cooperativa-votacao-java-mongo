package com.example.cooperative.integration.repository;

import com.example.cooperative.integration.model.entity.SessionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SessionMongoRepository extends MongoRepository<SessionEntity, String> {

    List<SessionEntity> findAllByIsOpenedTrue();

}
