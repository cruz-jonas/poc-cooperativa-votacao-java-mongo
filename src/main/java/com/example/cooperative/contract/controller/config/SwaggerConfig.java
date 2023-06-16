package com.example.cooperative.contract.controller.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                description = "Serviço responsável pela gestão de sessões de votação",
                version = "1.0.0",
                title = "Serviço de Gestão de Sessão de votação"
        )
)
public class SwaggerConfig {
}
