package com.example.cooperative.contract.controller;

import com.example.cooperative.impl.facade.AgendaFacade;
import com.example.cooperative.impl.facade.SessionFacade;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/agenda", produces = MediaType.APPLICATION_JSON_VALUE)
public class AgendaController {

    private final AgendaFacade agendaFacade;
    private final SessionFacade sessionFacade;

    @PostMapping()
    public void create() {
        agendaFacade.create();
    }

    @PostMapping(value = "/open-session")
    public void openSession() {
        sessionFacade.openSession();
    }

    @PostMapping(value = "/voto-register")
    public void registerVoto() {
        sessionFacade.registerVoto();
    }

    @GetMapping(value = "/agenda-result")
    public String generateResult() {
        return sessionFacade.generateResult();
    }
}
