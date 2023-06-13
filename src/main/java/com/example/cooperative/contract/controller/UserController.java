package com.example.cooperative.contract.controller;

import com.example.cooperative.contract.model.request.AgendaRequest;
import com.example.cooperative.impl.facade.UserFacade;
import com.example.cooperative.impl.model.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserFacade userFacade;
    private final ObjectMapper objectMapper;

    @PostMapping()
    public String create(@RequestBody AgendaRequest request) {
        return userFacade.create(objectMapper.convertValue(request, UserDTO.class));
    }

}
