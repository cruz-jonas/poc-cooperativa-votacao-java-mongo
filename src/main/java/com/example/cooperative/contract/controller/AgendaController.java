package com.example.cooperative.contract.controller;

import com.example.cooperative.contract.model.request.AgendaRequest;
import com.example.cooperative.contract.model.response.AgendaResponse;
import com.example.cooperative.impl.common.exception.APIException;
import com.example.cooperative.impl.facade.AgendaFacade;
import com.example.cooperative.impl.model.AgendaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Endpoints as Pautas")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/agenda", produces = MediaType.APPLICATION_JSON_VALUE)
public class AgendaController {

    private final AgendaFacade agendaFacade;
    private final ObjectMapper objectMapper;

    @Operation(
            method = "POST",
            summary = "Cria pautas para serem votadas",
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
    public String create(@RequestBody AgendaRequest request) {
        log.info("[AgendaController - create] Objeto recebido {}", request);
        return agendaFacade.create(objectMapper.convertValue(request, AgendaDTO.class));
    }

    @Operation(
            method = "GET",
            summary = "Buscar o resultado da votação",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sucesso na requisição",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AgendaResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = APIException.class))),
                    @ApiResponse(responseCode = "500", description = "Erro interno do Servidor",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = APIException.class))),
            }
    )
    @GetMapping(value = "/agenda-result/{idAgenda}")
    public AgendaResponse generateResult(@PathVariable("idAgenda") String idAgenda) {
        log.info("[AgendaController - generateResult] Objeto recebido {}", idAgenda);
        return objectMapper.convertValue(agendaFacade.findById(idAgenda), AgendaResponse.class);
    }

}
