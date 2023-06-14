package com.example.cooperative.impl.service;

import com.example.cooperative.impl.model.AgendaDTO;
import com.example.cooperative.integration.model.entity.AgendaEntity;
import com.example.cooperative.integration.repository.AgendaMongoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgendaServiceImplTest {

    @InjectMocks
    private AgendaServiceImpl agendaService;
    @Mock
    private AgendaMongoRepository agendaMongoRepository;
    @Mock
    private ObjectMapper objectMapper;

    @Test
    void create() {
        AgendaDTO dto = AgendaDTO.builder().name("teste").build();
        AgendaEntity entity = AgendaEntity.builder().build();

        when(agendaMongoRepository.save(any())).thenReturn(entity);

        agendaService.create(dto);

        verify(agendaMongoRepository, times(1)).save(any());

    }

    @Test
    void findById() {
        String id = UUID.randomUUID().toString();
        AgendaEntity entity = AgendaEntity.builder().build();

        when(agendaMongoRepository.findById(any())).thenReturn(Optional.of(entity));

        agendaService.findById(id);

        verify(agendaMongoRepository, times(1)).findById(any());
    }

    @Test
    void update() {
        AgendaDTO dto = AgendaDTO.builder().name("teste").build();
        AgendaEntity entity = AgendaEntity.builder().build();

        when(agendaMongoRepository.save(any())).thenReturn(entity);

        agendaService.update(dto);

        verify(agendaMongoRepository, times(1)).save(any());
    }
}