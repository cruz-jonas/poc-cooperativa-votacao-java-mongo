package com.example.cooperative.impl;

import com.example.cooperative.impl.model.AgendaDTO;
import com.example.cooperative.impl.model.SessionClosedRequest;
import com.example.cooperative.impl.model.SessionDTO;

import java.time.LocalDateTime;

public class SessionClosedMapper {

    public static SessionClosedRequest buildMessage(AgendaDTO agendaDTO, SessionDTO sessionDTO) {
        return SessionClosedRequest.builder()
                .idAgenda(agendaDTO.getId())
                .idSession(sessionDTO.getId())
                .nameAgenda(agendaDTO.getName())
                .dthrClosed(LocalDateTime.now())
                .votos(agendaDTO.getVotos())
                .build();
    }

}
