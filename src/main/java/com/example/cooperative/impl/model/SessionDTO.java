package com.example.cooperative.impl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SessionDTO {

    private String idAgenda;
    private Integer duration;

}
