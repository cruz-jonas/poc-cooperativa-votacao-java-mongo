package com.example.cooperative.impl.service;

import com.example.cooperative.impl.facade.AgendaFacade;
import com.example.cooperative.impl.facade.UserFacade;
import com.example.cooperative.impl.model.AgendaDTO;
import com.example.cooperative.impl.model.SessionDTO;
import com.example.cooperative.impl.model.UserDTO;
import com.example.cooperative.impl.model.VoteDTO;
import com.example.cooperative.integration.model.entity.SessionEntity;
import com.example.cooperative.integration.repository.SessionMongoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessionServiceImplTest {

    @InjectMocks
    private SessionServiceImpl sessionService;
    @Mock
    private SessionMongoRepository sessionMongoRepository;
    @Mock
    private AgendaFacade agendaFacade;
    @Mock
    private UserFacade userFacade;
    @Mock
    private ObjectMapper objectMapper;

    @Test
    void create() {
        SessionDTO sessionDTO = SessionDTO.builder().build();
        AgendaDTO agendaDTO = AgendaDTO.builder().build();
        SessionEntity entity = SessionEntity.builder().build();

        when(agendaFacade.findById(any())).thenReturn(agendaDTO);
        when(sessionMongoRepository.save(any())).thenReturn(entity);

        sessionService.create(sessionDTO);

        verify(agendaFacade, times(1)).findById(any());
        verify(sessionMongoRepository, times(1)).save(any());
    }

    @Test
    void registerVoto() {
        VoteDTO voteDTO = VoteDTO.builder()
                .choose("Sim")
                .idSession(UUID.randomUUID().toString()).build();
        SessionEntity entity = SessionEntity.builder()
                .isOpened(true)
                .createdAt(LocalDateTime.now().minusMinutes(3)).build();
        SessionDTO sessionDTO = SessionDTO.builder()
                .isOpened(true)
                .duration(4)
                .createdAt(LocalDateTime.now().minusMinutes(3)).build();
        UserDTO userDTO = UserDTO.builder().build();
        AgendaDTO agendaDTO = AgendaDTO.builder().build();
        agendaDTO.setVotos(new HashMap<>() {});
        agendaDTO.getVotos().put("Sim", 0);
        agendaDTO.getVotos().put("Não", 0);

        when(objectMapper.convertValue(Optional.of(entity), SessionDTO.class)).thenReturn(sessionDTO);
        when(sessionMongoRepository.findById(any())).thenReturn(Optional.of(entity));
        when(userFacade.findById(any())).thenReturn(userDTO);
        doNothing().when(userFacade).update(any());
        doNothing().when(agendaFacade).update(any());
        when(agendaFacade.findById(any())).thenReturn(agendaDTO);

        sessionService.registerVoto(voteDTO);

        verify(sessionMongoRepository, times(1)).findById(any());
        verify(userFacade, times(1)).findById(any());
        verify(userFacade, times(1)).update(any());
        verify(agendaFacade, times(1)).update(any());
        verify(agendaFacade, times(1)).findById(any());
    }

    @Test
    void findById() {
        String id = UUID.randomUUID().toString();
        SessionEntity entity = SessionEntity.builder().build();

        when(sessionMongoRepository.findById(any())).thenReturn(Optional.of(entity));

        sessionService.findById(id);

        verify(sessionMongoRepository, times(1)).findById(any());
    }
}