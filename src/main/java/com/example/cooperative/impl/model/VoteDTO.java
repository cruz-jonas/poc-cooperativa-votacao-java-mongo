package com.example.cooperative.impl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class VoteDTO {

    private String idAgenda;
    private String idSession;
    private String idUser;
    private String choose;

}
