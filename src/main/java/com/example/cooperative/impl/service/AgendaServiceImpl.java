package com.example.cooperative.impl.service;

import com.example.cooperative.impl.facade.AgendaFacade;
import com.example.cooperative.integration.model.entity.AgendaEntity;
import com.example.cooperative.integration.repository.AgendaMongoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AgendaServiceImpl implements AgendaFacade {

    private final AgendaMongoRepository agendaMongoRepository;

    @Override
    public void create() {
        agendaMongoRepository.save(AgendaEntity.builder().build());
    }
}
