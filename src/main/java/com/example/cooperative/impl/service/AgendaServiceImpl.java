package com.example.cooperative.impl.service;

import com.example.cooperative.impl.facade.AgendaFacade;
import com.example.cooperative.impl.model.AgendaDTO;
import com.example.cooperative.integration.model.entity.AgendaEntity;
import com.example.cooperative.integration.repository.AgendaMongoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AgendaServiceImpl implements AgendaFacade {

    private final AgendaMongoRepository agendaMongoRepository;
    private final ObjectMapper objectMapper;

    @Override
    public String create(AgendaDTO dto) {
        return agendaMongoRepository.save(objectMapper.convertValue(dto, AgendaEntity.class))
                .getId().toString();
    }

    @Override
    public AgendaDTO findById(String id) {
        return objectMapper.convertValue(agendaMongoRepository.findById(id), AgendaDTO.class);
    }
}
