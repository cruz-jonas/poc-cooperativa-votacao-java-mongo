package com.example.cooperative.contract.controller;

import com.example.cooperative.contract.model.request.UserRequest;
import com.example.cooperative.impl.common.exception.APIException;
import com.example.cooperative.impl.facade.UserFacade;
import com.example.cooperative.impl.model.UserDTO;
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

@Tag(name ="Endpoint para Usuários")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserFacade userFacade;
    private final ObjectMapper objectMapper;

    @Operation(
            method = "POST",
            summary = "Cria novo usuário",
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
    public String create(@RequestBody UserRequest request) {
        log.info("[UserController - create] Objeto recebido {}", request);
        return userFacade.create(objectMapper.convertValue(request, UserDTO.class));
    }

}
