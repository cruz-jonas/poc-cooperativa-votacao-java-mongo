package com.example.cooperative.impl.service;

import com.example.cooperative.impl.facade.UserFacade;
import com.example.cooperative.impl.model.UserDTO;
import com.example.cooperative.integration.model.entity.UserEntity;
import com.example.cooperative.integration.repository.UserMongoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserFacade {

    private final UserMongoRepository userMongoRepository;
    private final ObjectMapper objectMapper;


    @Override
    public UserDTO findById(String idUser) {
        return objectMapper.convertValue(userMongoRepository.findById(idUser), UserDTO.class);
    }

    @Transactional
    @Override
    public String create(UserDTO dto) {
        dto.setId(UUID.randomUUID().toString());
        return userMongoRepository.save(objectMapper.convertValue(dto, UserEntity.class)).getId();
    }

    @Transactional
    @Override
    public void update(UserDTO dto) {
        userMongoRepository.save(objectMapper.convertValue(dto, UserEntity.class));
    }
}
