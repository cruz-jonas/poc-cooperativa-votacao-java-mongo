package com.example.cooperative.contract.controller.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AgendaRequest {

    private String name;

}
