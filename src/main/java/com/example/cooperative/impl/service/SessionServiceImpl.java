package com.example.cooperative.impl.service;

import com.example.cooperative.impl.common.enumeration.VoteOptionEnum;
import com.example.cooperative.impl.exception.AgendaNotFoundException;
import com.example.cooperative.impl.exception.SessionClosedException;
import com.example.cooperative.impl.exception.SessionNotFoundException;
import com.example.cooperative.impl.exception.UserAlreadyVotedException;
import com.example.cooperative.impl.facade.AgendaFacade;
import com.example.cooperative.impl.facade.SessionFacade;
import com.example.cooperative.impl.facade.UserFacade;
import com.example.cooperative.impl.model.AgendaDTO;
import com.example.cooperative.impl.model.SessionDTO;
import com.example.cooperative.impl.model.UserDTO;
import com.example.cooperative.impl.model.VoteDTO;
import com.example.cooperative.integration.model.entity.SessionEntity;
import com.example.cooperative.integration.repository.SessionMongoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class SessionServiceImpl implements SessionFacade {

    private final SessionMongoRepository sessionMongoRepository;
    private final AgendaFacade agendaFacade;
    private final UserFacade userFacade;
    private final ObjectMapper objectMapper;
    private static final Integer SESSION_DURATION = 1;

    @Override
    public String create(SessionDTO dto) {
        validateAgenda(dto);
        validateDuration(dto);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setId(UUID.randomUUID().toString());
        log.info("[SessionServiceImpl - create] Objeto sendo persistido {}", dto);
        return saveSession(dto);
    }

    @Transactional
    private String saveSession(SessionDTO dto) {
        return sessionMongoRepository.save(objectMapper.convertValue(dto, SessionEntity.class)).getId();
    }

    @Override
    public void registerVoto(VoteDTO dto) {
        validateSession(dto);
        validateUser(dto);
        validateVoteType(dto);
        processAgenda(dto);

    }

    private void processAgenda(VoteDTO dto) {
        AgendaDTO agendaDTO = getAgendaById(dto);
        int currentValue = getCurrentValue(dto, agendaDTO);
        agendaDTO.getVotos().put(dto.getChoose(), currentValue + 1);
        updateVotes(agendaDTO);
    }

    @Transactional
    private void updateVotes(AgendaDTO agendaDTO) {
        log.info("[SessionServiceImpl - updateVotes] Objeto sendo atualizado {}", agendaDTO);
        agendaFacade.update(agendaDTO);
    }

    @Transactional
    private void updateUser(UserDTO userDTO) {
        log.info("[SessionServiceImpl - updateUser] Objeto sendo atualizado {}", userDTO);
        userFacade.update(userDTO);
    }

    private AgendaDTO getAgendaById(VoteDTO dto) {
        return agendaFacade.findById(dto.getIdAgenda());
    }

    private Integer getCurrentValue(VoteDTO dto, AgendaDTO agendaDTO) {
        return Objects.nonNull(agendaDTO.getVotos()) ? agendaDTO.getVotos().get(dto.getChoose()) : 0;
    }

    @Override
    public SessionDTO findById(String id) {
        return objectMapper.convertValue(sessionMongoRepository.findById(id), SessionDTO.class);
    }

    private void validateDuration(SessionDTO dto) {
        if(Objects.isNull(dto.getDuration())) {
            dto.setDuration(SESSION_DURATION);
        }
    }

    private void validateAgenda(SessionDTO dto) {
        log.info("[SessionServiceImpl - validateAgenda] Efetuando validacao da pauta {}", dto);
        AgendaDTO agendaDTO = agendaFacade.findById(dto.getIdAgenda());
        if(Objects.isNull(agendaDTO)) {
            log.error("[SessionServiceImpl - validateAgenda] Pauta nao encontrada {}", dto);
            throw new AgendaNotFoundException("Pauta não encontrada");
        }
    }

    private void validateSession(VoteDTO dto) {
        log.info("[SessionServiceImpl - validateSession] Efetuando validacao da sessao {}", dto);
        SessionDTO sessionDTO = findById(dto.getIdSession());
        if(Objects.isNull(sessionDTO)) {
            log.error("[SessionServiceImpl - validateSession] Sessao nao encontrada {}", dto);
            throw new SessionNotFoundException("Sessão não encontrada");
        }
        if(LocalDateTime.now().isAfter(sessionDTO.getCreatedAt().plusMinutes(sessionDTO.getDuration()))) {
            log.error("[SessionServiceImpl - validateSession] Sessao {} encerrada em {}", dto.getIdSession(),
                    sessionDTO.getCreatedAt().plusMinutes(sessionDTO.getDuration()));
            throw new SessionClosedException("Sessão encerrada em "+sessionDTO.getCreatedAt().plusMinutes(sessionDTO.getDuration()));
        }
    }

    private void validateVoteType(VoteDTO dto) {
        log.info("[SessionServiceImpl - validateVoteType] Efetuando validacao da opcao de voto {}", dto);
        VoteOptionEnum.findByDescription(dto.getChoose());
    }

    private void validateUser(VoteDTO dto) {
        log.info("[SessionServiceImpl - validateUser] Efetuando validacao de usuario {}", dto);
        UserDTO userDTO = userFacade.findById(dto.getIdUser());
        if(isAlreadyVoted(dto, userDTO)) {
            log.error("[SessionServiceImpl - validateUser] Usuario {} ja votou na sessao {}", dto.getIdUser(), dto.getIdSession());
            throw new UserAlreadyVotedException("Usuário já voltou nesta sessão");
        } else {
            userDTO.getVotedAgendas().add(dto.getIdAgenda());
            updateUser(userDTO);
        }
    }

    private boolean isAlreadyVoted(VoteDTO dto, UserDTO userDTO) {
        if(Objects.isNull(userDTO.getVotedAgendas())) {
            userDTO.setVotedAgendas(new ArrayList<>());
            return false;
        }
        return userDTO.getVotedAgendas().stream()
                .anyMatch(agenda -> agenda.equals(dto.getIdAgenda()));
    }

}
