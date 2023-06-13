package com.example.cooperative.contract.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SessionRequest {

    private String idAgenda;
    private Integer duration;

}
