package com.example.cooperative.contract.controller;

import com.example.cooperative.contract.model.request.AgendaRequest;
import com.example.cooperative.contract.model.response.AgendaResponse;
import com.example.cooperative.impl.facade.AgendaFacade;
import com.example.cooperative.impl.model.AgendaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/agenda", produces = MediaType.APPLICATION_JSON_VALUE)
public class AgendaController {

    private final AgendaFacade agendaFacade;
    private final ObjectMapper objectMapper;

    @PostMapping()
    public String create(@RequestBody AgendaRequest request) {
        log.info("[AgendaController - create] Objeto recebido {}", request);
        return agendaFacade.create(objectMapper.convertValue(request, AgendaDTO.class));
    }

    @GetMapping(value = "/agenda-result/{idAgenda}")
    public AgendaResponse generateResult(@PathVariable("idAgenda") String idAgenda) {
        log.info("[AgendaController - generateResult] Objeto recebido {}", idAgenda);
        return objectMapper.convertValue(agendaFacade.findById(idAgenda), AgendaResponse.class);
    }

}
