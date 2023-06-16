package com.example.cooperative.contract.controller;

import com.example.cooperative.contract.model.request.SessionRequest;
import com.example.cooperative.contract.model.request.VoteRequest;
import com.example.cooperative.impl.common.exception.APIException;
import com.example.cooperative.impl.facade.SessionFacade;
import com.example.cooperative.impl.model.SessionDTO;
import com.example.cooperative.impl.model.VoteDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Endpoints para Sessões e Voto")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/session", produces = MediaType.APPLICATION_JSON_VALUE)
public class SessionController {

    private final SessionFacade sessionFacade;
    private final ObjectMapper objectMapper;

    @Operation(
            method = "POST",
            summary = "Cria/abre sessão para pauta ser votada",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Sucesso na requisição",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = APIException.class))),
                    @ApiResponse(responseCode = "500", description = "Erro interno do Servidor",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = APIException.class))),
            }
    )
    @PostMapping()
    public String create(@RequestBody SessionRequest request) {
        log.info("[SessionController - create] Objeto recebido {}", request);
        return sessionFacade.create(objectMapper.convertValue(request, SessionDTO.class));
    }

    @Operation(
            method = "POST",
            summary = "Registra o voto do cooperado",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Sucesso na requisição",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = APIException.class))),
                    @ApiResponse(responseCode = "500", description = "Erro interno do Servidor",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = APIException.class))),
            }
    )
    @PostMapping(value = "/voto-register")
    public void registerVoto(@RequestBody VoteRequest request) {
        log.info("[SessionController - registerVoto] Objeto recebido {}", request);
        sessionFacade.registerVoto(objectMapper.convertValue(request, VoteDTO.class));
    }

}
