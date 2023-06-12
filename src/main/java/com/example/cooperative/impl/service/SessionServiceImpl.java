package com.example.cooperative.impl.service;

import com.example.cooperative.impl.exception.AgendaNotFoundException;
import com.example.cooperative.impl.facade.AgendaFacade;
import com.example.cooperative.impl.facade.SessionFacade;
import com.example.cooperative.impl.model.AgendaDTO;
import com.example.cooperative.impl.model.SessionDTO;
import com.example.cooperative.integration.model.entity.SessionEntity;
import com.example.cooperative.integration.repository.SessionMongoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
public class SessionServiceImpl implements SessionFacade {

    private final SessionMongoRepository sessionMongoRepository;
    private final AgendaFacade agendaFacade;
    private static final Integer SESSION_DURATION = 1;

    @Override
    public String openSession(SessionDTO dto) {
        verifyAgenda(dto);
        durationVerify(dto);
        SessionEntity entity = SessionEntity.builder()
                .agenda(dto.getIdAgenda())
                .duration(LocalDateTime.now().plusMinutes(dto.getDuration()))
                .build();
        return sessionMongoRepository.save(entity).getId().toString();
    }

    @Override
    public void registerVoto() {

    }

    @Override
    public String generateResult() {
        return null;
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

}
