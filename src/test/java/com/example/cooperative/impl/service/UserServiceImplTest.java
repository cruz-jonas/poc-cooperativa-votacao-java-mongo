package com.example.cooperative.impl.service;

import com.example.cooperative.impl.model.UserDTO;
import com.example.cooperative.integration.model.entity.UserEntity;
import com.example.cooperative.integration.repository.UserMongoRepository;
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
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserMongoRepository userMongoRepository;
    @Mock
    private ObjectMapper objectMapper;

    @Test
    void findById() {
        String id = UUID.randomUUID().toString();
        UserEntity entity = UserEntity.builder().build();

        when(userMongoRepository.findById(any())).thenReturn(Optional.of(entity));

        userService.findById(id);

        verify(userMongoRepository, times(1)).findById(any());
    }

    @Test
    void create() {
        UserDTO dto = UserDTO.builder().name("teste").build();
        UserEntity entity = UserEntity.builder().build();

        when(userMongoRepository.save(any())).thenReturn(entity);

        userService.create(dto);

        verify(userMongoRepository, times(1)).save(any());
    }

    @Test
    void update() {
        UserDTO dto = UserDTO.builder().name("teste").build();
        UserEntity entity = UserEntity.builder().build();

        when(userMongoRepository.save(any())).thenReturn(entity);

        userService.create(dto);

        verify(userMongoRepository, times(1)).save(any());
    }
}