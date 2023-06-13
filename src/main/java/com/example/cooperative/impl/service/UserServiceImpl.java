package com.example.cooperative.impl.service;

import com.example.cooperative.impl.facade.UserFacade;
import com.example.cooperative.impl.model.UserDTO;
import com.example.cooperative.integration.model.entity.UserEntity;
import com.example.cooperative.integration.repository.UserMongoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserFacade {

    private final UserMongoRepository userMongoRepository;
    private final ObjectMapper objectMapper;


    @Override
    public UserDTO findById(String idUser) {
        log.info("[UserServiceImpl - findById] Efetuando busca pelo id {}", idUser);
        return objectMapper.convertValue(userMongoRepository.findById(idUser), UserDTO.class);
    }

    @Transactional
    @Override
    public String create(UserDTO dto) {
        dto.setId(UUID.randomUUID().toString());
        log.info("[UserServiceImpl - create] Objeto sendo persistido {}", dto);
        return userMongoRepository.save(objectMapper.convertValue(dto, UserEntity.class)).getId();
    }

    @Transactional
    @Override
    public void update(UserDTO dto) {
        log.info("[UserServiceImpl - update] Objeto sendo atualizado {}", dto);
        userMongoRepository.save(objectMapper.convertValue(dto, UserEntity.class));
    }
}
