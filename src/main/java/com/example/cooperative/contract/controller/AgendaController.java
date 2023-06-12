package com.example.cooperative.contract.controller;

import com.example.cooperative.contract.controller.model.request.AgendaRequest;
import com.example.cooperative.impl.facade.AgendaFacade;
import com.example.cooperative.impl.model.AgendaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/agenda", produces = MediaType.APPLICATION_JSON_VALUE)
public class AgendaController {

    private final AgendaFacade agendaFacade;
    private final ObjectMapper objectMapper;

    @PostMapping()
    public String create(@RequestBody AgendaRequest request) {
        return agendaFacade.create(objectMapper.convertValue(request, AgendaDTO.class));
    }

}
