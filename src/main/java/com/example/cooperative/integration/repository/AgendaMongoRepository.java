package com.example.cooperative.integration.repository;


import com.example.cooperative.integration.model.entity.AgendaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaMongoRepository extends MongoRepository<AgendaEntity, String> {
}
