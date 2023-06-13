package com.example.cooperative.impl.service;

import com.example.cooperative.impl.facade.AgendaFacade;
import com.example.cooperative.impl.model.AgendaDTO;
import com.example.cooperative.integration.model.entity.AgendaEntity;
import com.example.cooperative.integration.repository.AgendaMongoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AgendaServiceImpl implements AgendaFacade {

    private final AgendaMongoRepository agendaMongoRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    @Override
    public String create(AgendaDTO dto) {
        dto.setId(UUID.randomUUID().toString());
        dto.setVotos(new HashMap<>() {});
        dto.getVotos().put("Sim", 0);
        dto.getVotos().put("Não", 0);
        return agendaMongoRepository.save(objectMapper.convertValue(dto, AgendaEntity.class)).getId();
    }

    @Override
    public AgendaDTO findById(String id) {
        return objectMapper.convertValue(agendaMongoRepository.findById(id), AgendaDTO.class);
    }

    @Transactional
    @Override
    public void update(AgendaDTO dto) {
        agendaMongoRepository.save(objectMapper.convertValue(dto, AgendaEntity.class));
    }
}
