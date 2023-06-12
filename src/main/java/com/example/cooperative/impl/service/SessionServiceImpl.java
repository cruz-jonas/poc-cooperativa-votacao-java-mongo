package com.example.cooperative.impl.service;

import com.example.cooperative.impl.exception.AgendaNotFoundException;
import com.example.cooperative.impl.exception.SessionClosedException;
import com.example.cooperative.impl.exception.SessionNotFoundException;
import com.example.cooperative.impl.facade.AgendaFacade;
import com.example.cooperative.impl.facade.SessionFacade;
import com.example.cooperative.impl.model.AgendaDTO;
import com.example.cooperative.impl.model.SessionDTO;
import com.example.cooperative.impl.model.VoteDTO;
import com.example.cooperative.integration.model.entity.SessionEntity;
import com.example.cooperative.integration.repository.SessionMongoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
public class SessionServiceImpl implements SessionFacade {

    private final SessionMongoRepository sessionMongoRepository;
    private final AgendaFacade agendaFacade;
    private final ObjectMapper objectMapper;
    private static final Integer SESSION_DURATION = 1;

    @Override
    public String openSession(SessionDTO dto) {
        verifyAgenda(dto);
        durationVerify(dto);
        dto.setCreatedAt(LocalDateTime.now());
        return sessionMongoRepository.save(objectMapper.convertValue(dto, SessionEntity.class))
                .getId().toString();
    }

    @Override
    public void registerVoto(VoteDTO dto) {
        validateSession(dto);
        AgendaDTO agendaDTO = agendaFacade.findById(dto.getIdAgenda());
        int currentValue = getCurrentValue(dto, agendaDTO);
        agendaDTO.getVotos().put(dto.getChoose(), currentValue + 1);
        agendaFacade.update(agendaDTO);

    }

    private Integer getCurrentValue(VoteDTO dto, AgendaDTO agendaDTO) {
        return Objects.nonNull(agendaDTO.getVotos()) ? agendaDTO.getVotos().get(dto.getChoose()) : 0;
    }

    @Override
    public String generateResult() {
        return null;
    }

    @Override
    public SessionDTO findById(String id) {
        return objectMapper.convertValue(sessionMongoRepository.findById(id), SessionDTO.class);
    }

    private void durationVerify(SessionDTO dto) {
        if(Objects.isNull(dto.getDuration())) {
            dto.setDuration(SESSION_DURATION);
        }
    }

    private void verifyAgenda(SessionDTO dto) {
        AgendaDTO agendaDTO = agendaFacade.findById(dto.getIdAgenda());
        if(Objects.isNull(agendaDTO)) {
            throw new AgendaNotFoundException("Pauta não encontrada");
        }
    }

    private void validateSession(VoteDTO dto) {
        SessionDTO sessionDTO = findById(dto.getIdSession());
        if(Objects.isNull(sessionDTO)) {
            throw new SessionNotFoundException("Sessão não encontrada");
        }
        if(LocalDateTime.now().isAfter(sessionDTO.getCreatedAt().plusMinutes(sessionDTO.getDuration()))) {
            throw new SessionClosedException("Sessão encerrada em "+sessionDTO.getCreatedAt().plusMinutes(sessionDTO.getDuration()));
        }
    }

}
