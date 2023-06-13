package com.example.cooperative.contract.controller;

import com.example.cooperative.contract.model.request.SessionRequest;
import com.example.cooperative.contract.model.request.VoteRequest;
import com.example.cooperative.impl.facade.SessionFacade;
import com.example.cooperative.impl.model.SessionDTO;
import com.example.cooperative.impl.model.VoteDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/session", produces = MediaType.APPLICATION_JSON_VALUE)
public class SessionController {

    private final SessionFacade sessionFacade;
    private final ObjectMapper objectMapper;

    @PostMapping()
    public String create(@RequestBody SessionRequest request) {
        return sessionFacade.create(objectMapper.convertValue(request, SessionDTO.class));
    }

    @PostMapping(value = "/voto-register")
    public void registerVoto(@RequestBody VoteRequest request) {
        sessionFacade.registerVoto(objectMapper.convertValue(request, VoteDTO.class));
    }

}
